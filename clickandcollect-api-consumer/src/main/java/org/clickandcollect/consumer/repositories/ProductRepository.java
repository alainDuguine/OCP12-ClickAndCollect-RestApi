package org.clickandcollect.consumer.repositories;

import org.clickandcollect.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByRestaurantId(Long id);
    @Query("SELECT p FROM Product p JOIN FETCH p.restaurant r WHERE r.id = :id")
    List<Product> getProductByRestaurantJoinFetch(Long id);
}
