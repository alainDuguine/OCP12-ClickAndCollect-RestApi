package org.clickandcollect.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.OrderService;
import org.clickandcollect.business.exception.UnknownResourceException;
import org.clickandcollect.consumer.repository.MenuRepository;
import org.clickandcollect.consumer.repository.OrderRepository;
import org.clickandcollect.consumer.repository.ProductInCourseRepository;
import org.clickandcollect.consumer.repository.ProductRepository;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.clickandcollect.model.entity.ClientOrder;
import org.clickandcollect.model.entity.MenuOrder;
import org.clickandcollect.model.entity.ProductOrder;
import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.model.entity.SelectedProduct;
import org.springframework.stereotype.Service;

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
        Long startTime = System.currentTimeMillis();
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

        this.orderRepository.save(clientOrder);
        Long stopTime = System.currentTimeMillis();
        log.info("Execution time '{}'ms", stopTime - startTime);
        return clientOrder;
    }

}
