package org.clickandcollect.webservice.mapper;

import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.dto.RegistrationFormDto;
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
}
