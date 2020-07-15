package org.clickandcollect.consumer.repository;

import org.clickandcollect.model.entity.Restaurant;
import org.locationtech.jts.geom.Geometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findRestaurantByEmail(String email);
    @Query("SELECT r FROM Restaurant r WHERE within(r.location, :filter) = true")
    List<Restaurant> findRestaurantWithin(@Param("filter") Geometry filter);
}
