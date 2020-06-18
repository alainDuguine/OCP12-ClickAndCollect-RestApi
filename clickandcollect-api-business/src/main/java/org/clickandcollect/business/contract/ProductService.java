package org.clickandcollect.business.contract;

import org.clickandcollect.model.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findProductsByRestaurantId(Long restaurantId, String category);
    Product findProductByIds(Long restaurantId, Long productId);
    Product saveProduct(Long id, Product product);
    Product updateProduct(Long restaurantId, Long productId, Product product);
    void deleteProduct(Long restaurantId, Long productId);
}
