package org.clickandcollect.webservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.RestaurantService;
import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.dto.RestaurantFullDto;
import org.clickandcollect.webservice.mapper.MenuMapper;
import org.clickandcollect.webservice.mapper.ProductMapper;
import org.clickandcollect.webservice.mapper.RestaurantMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@Slf4j
public class OrderApiController {

    public static final String BASE_URL = "/orders";

    private final RestaurantService restaurantService;
    private final RestaurantMapper restaurantMapper;
    private final ProductMapper productMapper;
    private final MenuMapper menuMapper;

    public OrderApiController(RestaurantService restaurantService, RestaurantMapper restaurantMapper, ProductMapper productMapper, MenuMapper menuMapper) {
        this.restaurantService = restaurantService;
        this.restaurantMapper = restaurantMapper;
        this.productMapper = productMapper;
        this.menuMapper = menuMapper;
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<RestaurantFullDto> getRestaurantForOrder(@PathVariable Long restaurantId) {
        log.info("Retrieving restaurant infos for id '{}'", restaurantId);
        Restaurant restaurant = this.restaurantService.findRestaurantById(restaurantId);
        RestaurantFullDto restaurantFullDto = RestaurantFullDto.builder()
                .restaurant(this.restaurantMapper.restaurantToRestaurantDto(restaurant))
                .products(this.productMapper.listProductToListProductDto(restaurant.getProducts()))
                .menus(this.menuMapper.listMenuToListMenuDto(restaurant.getMenus()))
                .build();
        return new ResponseEntity<>(restaurantFullDto, HttpStatus.OK);
    }
}
