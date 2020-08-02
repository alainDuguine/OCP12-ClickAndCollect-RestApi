package org.clickandcollect.business.impl;

import org.clickandcollect.business.contract.RestaurantService;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.clickandcollect.model.entity.BusinessHour;
import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.ClickAndCollectApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = ClickAndCollectApiApplication.class)
@TestPropertySource(locations = {"classpath:/application-test.properties"})
public class RestaurantServiceIT {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private RestaurantService restaurantService;

    @Test
    @Transactional
    public void givenRestaurantWithoutMenusAndProducts_whenUpdateRestaurant_shouldNotDeleteChildFields() {
        Restaurant restaurant = this.restaurantRepository.findById(1L).get();

        Restaurant restaurantUpdate = Restaurant.builder()
                .name("Updated name")
                .description("Updated description")
                .password("updatedpassword")
                .formattedAddress(restaurant.getFormattedAddress())
                .latitude(restaurant.getLatitude())
                .longitude(restaurant.getLongitude())
                .build();

        restaurantUpdate = this.restaurantService.updateRestaurant(1L, restaurantUpdate);

        assertThat(restaurantUpdate.getId()).isEqualTo(restaurant.getId());
        assertThat(restaurantUpdate.getName()).isEqualTo("Updated name");
        assertThat(restaurantUpdate.getDescription()).isEqualTo("Updated description");
        // Password can't be changed by update method
        assertThat(restaurantUpdate.getPassword()).isEqualTo(restaurant.getPassword());
        assertThat(restaurantUpdate.getFormattedAddress()).isEqualTo(restaurant.getFormattedAddress());
        assertThat(restaurantUpdate.getLatitude()).isEqualTo(restaurant.getLatitude());
        assertThat(restaurantUpdate.getLongitude()).isEqualTo(restaurant.getLongitude());
        assertThat(restaurantUpdate.getProducts()).isEqualTo(restaurant.getProducts());
        assertThat(restaurantUpdate.getMenus()).isEqualTo(restaurant.getMenus());

    }

    @Test
    @Transactional
    public void givenRestaurantWithEmptyFields_whenUpdateRestaurant_shouldNotThrowsException() {
        Restaurant restaurant = this.restaurantRepository.findById(1L).get();

        Restaurant restaurantUpdate = Restaurant.builder()
                .name("Updated name")
                .build();

        restaurantUpdate = this.restaurantService.updateRestaurant(1L, restaurantUpdate);

        assertThat(restaurantUpdate.getId()).isEqualTo(restaurant.getId());
        assertThat(restaurantUpdate.getName()).isEqualTo("Updated name");
        assertThat(restaurantUpdate.getDescription()).isNull();
        assertThat(restaurantUpdate.getFormattedAddress()).isNull();
        assertThat(restaurantUpdate.getLatitude()).isNull();
        assertThat(restaurantUpdate.getLongitude()).isNull();
    }

    @Test
    @Transactional
    public void givenRestaurantWithBusinessHours_whenUpdateRestaurant_shouldPersistWithGoodValues() {
        Restaurant restaurant = this.restaurantRepository.findById(1L).get();

        BusinessHour businessHour = BusinessHour.builder()
                .startDay(DayOfWeek.MONDAY)
                .endDay(DayOfWeek.FRIDAY)
                .startTime(LocalTime.of(8,0))
                .endTime(LocalTime.of(18,0))
                .build();

        BusinessHour businessHour1 = BusinessHour.builder()
                .startDay(DayOfWeek.SATURDAY)
                .endDay(DayOfWeek.SUNDAY)
                .startTime(LocalTime.of(11,30))
                .endTime(LocalTime.of(15,30))
                .build();

        List<BusinessHour> businessHours = new ArrayList<>();
        businessHours.add(businessHour);
        businessHours.add(businessHour1);

        restaurant.setBusinessHours(businessHours);

        Restaurant restaurantAfterUpdate = this.restaurantService.updateRestaurant(restaurant.getId(), restaurant);

        assertThat(restaurantAfterUpdate.getBusinessHours()).isEqualTo(businessHours);

        Restaurant restaurant1 = this.restaurantService.findRestaurantById(restaurant.getId());

        assertThat(restaurant1.getBusinessHours()).isEqualTo(businessHours);
    }

    @Test
    public void givenPosition_whenSearchForRestaurantWithinCircle_thenReturnGoodDistanceRestaurants() {
        List<Restaurant> restaurants = this.restaurantService.findRestaurantsWithin(48.868924,2.402176,5);
        assertThat(restaurants.size()).isEqualTo(8);
        assertThat(restaurants).isSortedAccordingTo(Comparator.comparingDouble(Restaurant::getDistance));
        restaurants.forEach(restaurant -> assertThat(restaurant.getDistance()).isLessThan(5));
    }

    @Test
    public void givenOOBPosition_whenSearchForRestaurantWithinCircle_thenReturnNoRestaurants() {
        List<Restaurant> restaurants = this.restaurantService.findRestaurantsWithin(43.553386,-0.658295,5);
        assertThat(restaurants.size()).isEqualTo(0);
    }

}
