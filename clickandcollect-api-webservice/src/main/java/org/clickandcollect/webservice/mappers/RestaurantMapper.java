package org.clickandcollect.webservice.mappers;

import org.clickandcollect.model.entities.Restaurant;
import org.clickandcollect.webservice.dtos.RestaurantDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);
    Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto);
}
