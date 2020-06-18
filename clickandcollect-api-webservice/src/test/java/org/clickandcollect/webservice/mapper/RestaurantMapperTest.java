package org.clickandcollect.webservice.mapper;

import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.dto.RegistrationFormDto;
import org.clickandcollect.webservice.dto.RestaurantDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantMapperTest {

    RestaurantMapper restaurantMapper = Mappers.getMapper(RestaurantMapper.class);

    @Test
    void givenRegisterForm_whenMappingToRestaurantModel_thenGetValidObject() {
        RegistrationFormDto registrationFormDto = RegistrationFormDto.builder()
                .name("Test")
                .email("m@ail.com")
                .password("password")
                .build();

        Restaurant restaurant = this.restaurantMapper.registerFormToRestaurant(registrationFormDto);

        assertThat(restaurant.getName()).isEqualTo(registrationFormDto.getName());
        assertThat(restaurant.getEmail()).isEqualTo(registrationFormDto.getEmail());
        assertThat(restaurant.getPassword()).isEqualTo(registrationFormDto.getPassword());
    }

    @Test
    void givenRestaurantModel_whenMappingToRestaurantDto_thenGetValidObject() {
        Restaurant restaurant = Restaurant.builder()
                .id(1L)
                .email("em@ail.com")
                .name("Test restaurant")
                .password("p@ssword")
                .description("Test description")
                .typeCuisine("Française")
                .formattedAddress("1 avenu du général de Gaulle, 75001, Paris")
                .latitude("48.065789")
                .longitude("2.859765")
                .build();

        RestaurantDto restaurantDto = this.restaurantMapper.restaurantToRestaurantDto(restaurant);

        assertThat(restaurantDto.getId()).isEqualTo(restaurant.getId());
        assertThat(restaurantDto.getEmail()).isEqualTo(restaurant.getEmail());
        assertThat(restaurantDto.getName()).isEqualTo(restaurant.getName());
        assertThat(restaurantDto.getPassword()).isEqualTo(restaurant.getPassword());
        assertThat(restaurantDto.getDescription()).isEqualTo(restaurant.getDescription());
        assertThat(restaurantDto.getTypeCuisine()).isEqualTo(restaurant.getTypeCuisine());
        assertThat(restaurantDto.getFormattedAddress()).isEqualTo(restaurant.getFormattedAddress());
        assertThat(restaurantDto.getLatitude()).isEqualTo(restaurant.getLatitude());
        assertThat(restaurantDto.getLongitude()).isEqualTo(restaurant.getLongitude());
    }

    @Test
    void givenRestaurantDto_whenMappingToRestaurantModel_thenGetValidObject() {
        RestaurantDto restaurantDto = RestaurantDto.builder()
                .id(1L)
                .email("em@ail.com")
                .name("Test restaurant")
                .password("p@ssword")
                .description("Test description")
                .typeCuisine("Française")
                .formattedAddress("1 avenu du général de Gaulle, 75001, Paris")
                .latitude("48.065789")
                .longitude("2.859765")
                .build();

        Restaurant restaurant = this.restaurantMapper.restaurantDtoToRestaurant(restaurantDto);

        assertThat(restaurant.getId()).isEqualTo(restaurantDto.getId());
        assertThat(restaurant.getEmail()).isEqualTo(restaurantDto.getEmail());
        assertThat(restaurant.getName()).isEqualTo(restaurantDto.getName());
        assertThat(restaurant.getPassword()).isEqualTo(restaurantDto.getPassword());
        assertThat(restaurant.getDescription()).isEqualTo(restaurantDto.getDescription());
        assertThat(restaurant.getTypeCuisine()).isEqualTo(restaurantDto.getTypeCuisine());
        assertThat(restaurant.getFormattedAddress()).isEqualTo(restaurantDto.getFormattedAddress());
        assertThat(restaurant.getLatitude()).isEqualTo(restaurantDto.getLatitude());
        assertThat(restaurant.getLongitude()).isEqualTo(restaurantDto.getLongitude());
    }
}
