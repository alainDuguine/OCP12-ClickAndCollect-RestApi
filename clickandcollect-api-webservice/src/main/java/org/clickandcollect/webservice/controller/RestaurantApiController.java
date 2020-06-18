package org.clickandcollect.webservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.RestaurantService;
import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.dto.RestaurantDto;
import org.clickandcollect.webservice.mapper.RestaurantMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/restaurants")
@Slf4j
public class RestaurantApiController {

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;

    public RestaurantApiController(RestaurantService restaurantService, RestaurantMapper restaurantMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
    }

    @GetMapping("{restaurantId}")
    public ResponseEntity<RestaurantDto> getRestaurant(@PathVariable Long restaurantId) {
        log.info("Retrieving the restaurant '{}'", restaurantId);
        Restaurant restaurant = this.restaurantService.findRestaurantById(restaurantId);
        log.info("Restaurant '{}' found", restaurant.getId());
        return new ResponseEntity<>(this.restaurantMapper.restaurantToRestaurantDto(restaurant), HttpStatus.OK);
    }
}
