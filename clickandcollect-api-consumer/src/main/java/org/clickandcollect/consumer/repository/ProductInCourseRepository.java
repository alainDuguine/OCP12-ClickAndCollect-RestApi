package org.clickandcollect.consumer.repository;

import org.clickandcollect.model.entity.ProductInCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductInCourseRepository extends JpaRepository<ProductInCourse, Long> {
    void deleteAllByProductId(Long productId);
}
