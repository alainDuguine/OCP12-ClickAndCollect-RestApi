package org.clickandcollect.business.contract;

import org.clickandcollect.model.entities.Product;

import java.util.List;

public interface RestaurantService {
    Product saveProduct(Long id, Product product);
    Product updateProduct(Long productId, Long restaurantId, Product product);
    void deleteProduct(Long productId, Long restaurantId);
    List<Product> findProductsByRestaurantId(Long restaurantId);
    Product findProductByIds(Long restaurantId, Long productId);
}
