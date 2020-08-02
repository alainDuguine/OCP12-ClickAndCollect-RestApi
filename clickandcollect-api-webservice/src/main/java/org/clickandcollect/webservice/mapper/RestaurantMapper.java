package org.clickandcollect.webservice.mapper;

import org.clickandcollect.model.entity.BusinessHour;
import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.dto.BusinessHourDto;
import org.clickandcollect.webservice.dto.RegistrationFormDto;
import org.clickandcollect.webservice.dto.RestaurantDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.DayOfWeek;
import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {

    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);
    List<RestaurantDto> restaurantsToDto(List<Restaurant> restaurants);

    @Mapping(target = "distance", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "photo", ignore = true)
    @Mapping(target = "locked", ignore = true)
    @Mapping(target = "expired", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "businessHours", ignore = true)
    @Mapping(target = "typeCuisine", ignore = true)
    @Mapping(target = "longitude", ignore = true)
    @Mapping(target = "latitude", ignore = true)
    @Mapping(target = "formattedAddress", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Mapping(target = "menus", ignore = true)
    @Mapping(target = "id", ignore = true)
    Restaurant registerFormToRestaurant(RegistrationFormDto registerForm);

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "locked", ignore = true)
    @Mapping(target = "expired", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "menus", ignore = true)
    @Mapping(target = "products", ignore = true)
    Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto);

    default DayOfWeek integerToDay(Integer day){
        return DayOfWeek.of(day);
    }

    default Integer dayToInteger(DayOfWeek day){
        return day.getValue();
    }

    @Mapping(source = "businessHour.startTime", target = "startTime", dateFormat = "HH:mm")
    @Mapping(source = "businessHour.endTime", target = "endTime", dateFormat = "HH:mm")
    BusinessHourDto businessToBusinessDto(BusinessHour businessHour);

}
