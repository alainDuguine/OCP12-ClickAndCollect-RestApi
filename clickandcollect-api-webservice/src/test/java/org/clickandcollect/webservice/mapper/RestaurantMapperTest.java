package org.clickandcollect.webservice.mapper;

import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.dto.RegisterFormDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantMapperTest {

    RestaurantMapper restaurantMapper = Mappers.getMapper(RestaurantMapper.class);

    @Test
    void givenRegisterForm_whenMappingToRestaurantModel_thenGetValidObject() {
        RegisterFormDto registerFormDto = RegisterFormDto.builder()
                .name("Test")
                .email("m@ail.com")
                .password("password")
                .build();

        Restaurant restaurant = this.restaurantMapper.registerFormToRestaurant(registerFormDto);

        assertThat(restaurant.getName()).isEqualTo(registerFormDto.getName());
        assertThat(restaurant.getEmail()).isEqualTo(registerFormDto.getEmail());
        assertThat(restaurant.getPassword()).isEqualTo(registerFormDto.getPassword());
    }
}
