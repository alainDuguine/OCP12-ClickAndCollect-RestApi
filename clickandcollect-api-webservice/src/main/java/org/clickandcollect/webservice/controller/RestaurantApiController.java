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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping()
    public ResponseEntity<List<RestaurantDto>> getRestaurantWithin(@RequestParam("lat") Double latitude,
                                                                @RequestParam("long") Double longitude,
                                                                @RequestParam("rad") Integer radius) {
        List<Restaurant> restaurants = this.restaurantService.findRestaurantsWithin(latitude, longitude, radius);
        return new ResponseEntity<>(this.restaurantMapper.restaurantsToDto(restaurants), HttpStatus.OK);
    }

    @GetMapping("{restaurantId}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Long restaurantId) {
        log.info("Retrieving the restaurant '{}'", restaurantId);
        Restaurant restaurant = this.restaurantService.findRestaurantById(restaurantId);
        log.info("Restaurant '{}' found", restaurant.getId());
        return new ResponseEntity<>(this.restaurantMapper.restaurantToRestaurantDto(restaurant), HttpStatus.OK);
    }

    @PutMapping("{restaurantId}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable Long restaurantId,
                                                          @Valid @RequestBody RestaurantDto restaurantDto) {
        log.info("Updating restaurant id '{}'", restaurantId);
        Restaurant restaurant = this.restaurantService.updateRestaurant(
                restaurantId,
                this.restaurantMapper.restaurantDtoToRestaurant(restaurantDto)
        );
        log.info("Restaurant '{}' updated", restaurantId);
        return new ResponseEntity<>(this.restaurantMapper.restaurantToRestaurantDto(restaurant), HttpStatus.OK);
    }

    @PostMapping("{restaurantId}/upload")
    public ResponseEntity<RestaurantDto> uploadPhoto(@RequestParam("photo") MultipartFile photo, @PathVariable Long restaurantId) {
        log.info("Uploading photo for restaurant id '{}'", restaurantId);
        Restaurant restaurant = this.restaurantService.uploadPhotoRestaurant(restaurantId, photo);
        log.info("Photo uploaded for restaurant id '{}'", restaurantId);
        return new ResponseEntity<>(this.restaurantMapper.restaurantToRestaurantDto(restaurant), HttpStatus.OK);
    }
}
