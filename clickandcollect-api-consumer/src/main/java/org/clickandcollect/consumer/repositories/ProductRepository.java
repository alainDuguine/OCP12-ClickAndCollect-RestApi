package org.clickandcollect.consumer.repositories;

import org.clickandcollect.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByRestaurantId(Long id);
    Optional<Product> findProductByIdAndRestaurantId(Long productId, Long restaurantId);
}
