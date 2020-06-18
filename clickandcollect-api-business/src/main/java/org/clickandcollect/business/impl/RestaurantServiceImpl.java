package org.clickandcollect.business.impl;

import org.clickandcollect.business.contract.RestaurantService;
import org.clickandcollect.business.exception.UnknownResourceException;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.clickandcollect.model.entity.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) {
        return this.restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new UnknownResourceException("Unknown restaurant '" + restaurantId + "'")
        );
    }
}
