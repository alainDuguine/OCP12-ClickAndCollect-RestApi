package org.clickandcollect.webservice.mapper;

import org.clickandcollect.model.entity.BusinessHour;
import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.dto.BusinessHourDto;
import org.clickandcollect.webservice.dto.RegistrationFormDto;
import org.clickandcollect.webservice.dto.RestaurantDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

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

    @Test
    void givenBusinessHoursDto_whenMappingToRestaurantModel_thenGetvalidObject() {
        BusinessHourDto businessHourDto1 = BusinessHourDto
                .builder()
                .startDay(1)
                .endDay(7)
                .startTime("08:00")
                .endTime("23:30")
                .build();

        BusinessHourDto businessHourDto2 = BusinessHourDto
                .builder()
                .startDay(3)
                .endDay(6)
                .startTime("12:00")
                .endTime("17:30")
                .build();

        RestaurantDto restaurantDto = RestaurantDto.builder()
                .id(1L)
                .businessHours(Arrays.asList(businessHourDto1, businessHourDto2))
                .build();

        Restaurant restaurant = this.restaurantMapper.restaurantDtoToRestaurant(restaurantDto);

        List<BusinessHour> businessHours = restaurant.getBusinessHours();
        System.out.println(businessHours);

        assertThat(businessHours.get(0).getStartDay()).isEqualTo(DayOfWeek.of(businessHourDto1.getStartDay()));
        assertThat(businessHours.get(0).getEndDay()).isEqualTo(DayOfWeek.of(businessHourDto1.getEndDay()));
        assertThat(businessHours.get(0).getStartTime()).isEqualTo(LocalTime.parse(businessHourDto1.getStartTime()));
        assertThat(businessHours.get(0).getEndTime()).isEqualTo(LocalTime.parse(businessHourDto1.getEndTime()));

        assertThat(businessHours.get(1).getStartDay()).isEqualTo(DayOfWeek.of(businessHourDto2.getStartDay()));
        assertThat(businessHours.get(1).getEndDay()).isEqualTo(DayOfWeek.of(businessHourDto2.getEndDay()));
        assertThat(businessHours.get(1).getStartTime()).isEqualTo(LocalTime.parse(businessHourDto2.getStartTime()));
        assertThat(businessHours.get(1).getEndTime()).isEqualTo(LocalTime.parse(businessHourDto2.getEndTime()));
    }

    @Test
    void givenBusinessHours_whenMappingToRestaurantDto_thenGetvalidObject() {
        BusinessHour businessHour1 = BusinessHour
                .builder()
                .startDay(DayOfWeek.MONDAY)
                .endDay(DayOfWeek.SUNDAY)
                .startTime(LocalTime.of(8,0))
                .endTime(LocalTime.of(23,30))
                .build();

        BusinessHour businessHour2 = BusinessHour
                .builder()
                .startDay(DayOfWeek.WEDNESDAY)
                .endDay(DayOfWeek.SATURDAY)
                .startTime(LocalTime.of(12,0))
                .endTime(LocalTime.of(17,30))
                .build();

        Restaurant restaurant = Restaurant.builder()
                .id(1L)
                .businessHours(Arrays.asList(businessHour1, businessHour2))
                .build();

        RestaurantDto restaurantDto = this.restaurantMapper.restaurantToRestaurantDto(restaurant);

        List<BusinessHourDto> businessHours = restaurantDto.getBusinessHours();
        System.out.println(businessHours);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        assertThat(businessHours.get(0).getStartDay()).isEqualTo(businessHour1.getStartDay().getValue());
        assertThat(businessHours.get(0).getEndDay()).isEqualTo(businessHour1.getEndDay().getValue());
        assertThat(businessHours.get(0).getStartTime()).isEqualTo(businessHour1.getStartTime().format(formatter));
        assertThat(businessHours.get(0).getEndTime()).isEqualTo(businessHour1.getEndTime().format(formatter));

        assertThat(businessHours.get(1).getStartDay()).isEqualTo(businessHour2.getStartDay().getValue());
        assertThat(businessHours.get(1).getEndDay()).isEqualTo(businessHour2.getEndDay().getValue());
        assertThat(businessHours.get(1).getStartTime()).isEqualTo(businessHour2.getStartTime().format(formatter));
        assertThat(businessHours.get(1).getEndTime()).isEqualTo(businessHour2.getEndTime().format(formatter));
    }
}
