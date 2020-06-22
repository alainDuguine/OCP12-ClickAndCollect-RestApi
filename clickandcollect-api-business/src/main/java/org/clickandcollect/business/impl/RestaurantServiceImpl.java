package org.clickandcollect.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.RestaurantService;
import org.clickandcollect.business.exception.UnknownResourceException;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.clickandcollect.model.entity.Restaurant;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) {
        log.info("Retrieving restaurant id '{}'", restaurantId);
        return this.restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new UnknownResourceException("Unknown restaurant '" + restaurantId + "'")
        );
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant) {
        log.info("Retrieving restaurant id '{}' for update", restaurantId);
        Restaurant restaurantInDb = this.restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new UnknownResourceException("Unknown restaurant '" + restaurantId + "'"));
        log.info("Restaurant found");
        restaurantInDb.setName(restaurant.getName());
        restaurantInDb.setDescription(restaurant.getDescription());
        restaurantInDb.setTypeCuisine(restaurant.getTypeCuisine());
        restaurantInDb.setFormattedAddress(restaurant.getFormattedAddress());
        restaurantInDb.setLatitude(restaurant.getLatitude());
        restaurantInDb.setLongitude(restaurant.getLongitude());
        restaurantInDb.addAllBusinessHours(restaurant.getBusinessHours());
        return this.restaurantRepository.save(restaurantInDb);
    }
}
