package org.clickandcollect.business.contract;

import org.clickandcollect.model.entities.Product;

public interface RestaurantService {
    Product addProduct(Long id, Product product);
    Product updateProduct(Long productId, Long restaurantId, Product product);
    void deleteProduct(Long productId, Long restaurantId);
}
