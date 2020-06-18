package org.clickandcollect.business.impl;

import org.clickandcollect.business.contract.RestaurantService;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.ClickAndCollectApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.transaction.Transactional;

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
}
