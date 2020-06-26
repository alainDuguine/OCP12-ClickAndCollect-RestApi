package org.clickandcollect.business.contract;

import org.clickandcollect.model.entity.Restaurant;
import org.springframework.web.multipart.MultipartFile;

public interface RestaurantService {
    Restaurant findRestaurantById(Long restaurantId);
    Restaurant updateRestaurant(Long restaurantId, Restaurant restaurant);
    void uploadPhotoRestaurant(Long restaurantId, MultipartFile photo);
}
