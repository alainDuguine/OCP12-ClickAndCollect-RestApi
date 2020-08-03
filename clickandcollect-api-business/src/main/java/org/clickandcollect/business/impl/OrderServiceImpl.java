package org.clickandcollect.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.OrderService;
import org.clickandcollect.business.exception.PickupDateTimeAttributeException;
import org.clickandcollect.business.exception.UnknownResourceException;
import org.clickandcollect.consumer.repository.MenuRepository;
import org.clickandcollect.consumer.repository.OrderRepository;
import org.clickandcollect.consumer.repository.ProductInCourseRepository;
import org.clickandcollect.consumer.repository.ProductRepository;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.clickandcollect.model.entity.BusinessHour;
import org.clickandcollect.model.entity.ClientOrder;
import org.clickandcollect.model.entity.MenuOrder;
import org.clickandcollect.model.entity.ProductOrder;
import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.model.entity.SelectedProduct;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final MenuRepository menuRepository;
    private final ProductRepository productRepository;
    private final ProductInCourseRepository productInCourseRepository;
    private final RestaurantRepository restaurantRepository;

    public OrderServiceImpl(OrderRepository orderRepository, MenuRepository menuRepository, ProductRepository productRepository, ProductInCourseRepository productInCourseRepository, RestaurantRepository restaurantRepository) {
        this.orderRepository = orderRepository;
        this.menuRepository = menuRepository;
        this.productRepository = productRepository;
        this.productInCourseRepository = productInCourseRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public ClientOrder saveOrder(Long restaurantId, ClientOrder clientOrder) {
        log.info("Saving order for restaurant '{}'", restaurantId);
        Restaurant restaurant = this.restaurantRepository.findById(restaurantId).orElseThrow(() ->
                new UnknownResourceException("Unknown Restaurant '" + restaurantId + "'")
        );
        restaurant.addOrder(clientOrder);

        for(MenuOrder menuOrder : clientOrder.getMenuOrders()) {
            menuOrder.setMenu(this.menuRepository.findById(menuOrder.getMenu().getId()).orElseThrow(() ->
                    new UnknownResourceException("Unknown Menu '" + menuOrder.getMenu().getId() + "'"))
            );
            menuOrder.setClientOrder(clientOrder);
            for (SelectedProduct selectedProduct : menuOrder.getSelectedProducts()) {
                selectedProduct.setProductInCourse(this.productInCourseRepository.findById(selectedProduct.getProductInCourse().getId()).orElseThrow(() ->
                        new UnknownResourceException("Unknown ProductInCourse '" + selectedProduct.getProductInCourse().getId() + "'")));
                selectedProduct.setMenuOrder(menuOrder);
            }
        }
        for(ProductOrder productOrder : clientOrder.getProductOrders()) {
            productOrder.setProduct(this.productRepository.findById(productOrder.getProduct().getId()).orElseThrow(() ->
                    new UnknownResourceException("Unknown Product '" + productOrder.getProduct().getId() + "'"))
            );
            productOrder.setClientOrder(clientOrder);
        }
        if(this.checkPickupDateTime(restaurant.getBusinessHours(), clientOrder.getPickupDateTime(), LocalDateTime.now())) {
            this.orderRepository.save(clientOrder);
        } else {
            throw new PickupDateTimeAttributeException("pickupDateTime " + clientOrder.getPickupDateTime() + " is invalid");
        }
        return clientOrder;
    }

    /**
     * public method to check that pickupDate
     * is included in opening hours of restaurant.businessHours
     * (dayToCompare will be usually LocalDate.now() but is specified mainly for unitary test purpose)
     * @param businessHourList list of opened hours for restaurants
     * @param pickupDateTime date and time when the order should be picked up
     * @param dayToCompare day to compare specified for test purposes;
     * @return true if valid
     */
    public boolean checkPickupDateTime(List<BusinessHour> businessHourList, LocalDateTime pickupDateTime, LocalDateTime dayToCompare) {
        boolean validPickupDateTime = false;
        for (BusinessHour businessHour : businessHourList) {
//           pickupDateTime should be today, and should be between startDay and endDay
            if (pickupDateTime.getDayOfWeek().equals(dayToCompare.getDayOfWeek())
                    && pickupDateTime.getDayOfWeek().getValue() >= businessHour.getStartDay().getValue()
                    && pickupDateTime.getDayOfWeek().getValue() <= businessHour.getEndDay().getValue()) {

//                pickupTime should be after LocalTime.now(), and between startTime and endTime of this businessDay
                if (pickupDateTime.toLocalTime().isAfter(businessHour.getStartTime())
                        && pickupDateTime.toLocalTime().isBefore(businessHour.getEndTime())
                        && pickupDateTime.toLocalTime().isAfter(dayToCompare.toLocalTime())) {
                    validPickupDateTime = true;
                }

            }
        }
        return validPickupDateTime;
    }

}
