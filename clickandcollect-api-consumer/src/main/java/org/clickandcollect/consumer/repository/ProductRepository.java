package org.clickandcollect.consumer.repository;

import org.clickandcollect.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByRestaurantId(Long id);
    @Query("SELECT p " +
            "FROM Product p " +
            "JOIN p.category c " +
            "WHERE p.restaurant.id = :id " +
            "AND (:category is null or p.category.name = :category)")
    List<Product> findAllByRestaurantIdAndCategoryName(@Param("id") Long restaurantId, @Param("category") String categoryName);
    Optional<Product> findProductByIdAndRestaurantId(Long productId, Long restaurantId);

}
