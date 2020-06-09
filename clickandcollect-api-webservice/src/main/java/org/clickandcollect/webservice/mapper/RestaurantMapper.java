package org.clickandcollect.webservice.mapper;

import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.dto.RestaurantDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {
    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);

    @Mapping(target = "menus", ignore = true)
    @Mapping(target = "products", ignore = true)
    Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto);
}
