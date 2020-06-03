package org.clickandcollect.business.contract;

import org.clickandcollect.model.entitie.Product;

import java.util.List;

public interface RestaurantService {
    Product saveProduct(Long id, Product product);
    Product updateProduct(Long restaurantId, Long productId, Product product);
    void deleteProduct(Long restaurantId, Long productId);
    List<Product> findProductsByRestaurantId(Long restaurantId);
    Product findProductByIds(Long restaurantId, Long productId);
}
