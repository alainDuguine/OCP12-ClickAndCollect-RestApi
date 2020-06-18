package org.clickandcollect.business.contract;

import org.clickandcollect.model.entity.Restaurant;

public interface RestaurantService {
    Restaurant findRestaurantById(Long restaurantId);
}
