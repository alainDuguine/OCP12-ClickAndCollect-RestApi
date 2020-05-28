package org.clickandcollect.webservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.RestaurantService;
import org.clickandcollect.consumer.repositories.ProductRepository;
import org.clickandcollect.model.entities.Product;
import org.clickandcollect.webservice.dtos.ProductDto;
import org.clickandcollect.webservice.mappers.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(RestaurantsApiController.RESOURCE_URL)
@Slf4j
public class RestaurantsApiController {

    public static final String RESOURCE_URL = "/restaurants";

    private final RestaurantService restaurantService;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public RestaurantsApiController(RestaurantService restaurantService, ProductRepository productRepository, ProductMapper productMapper) {
        this.restaurantService = restaurantService;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @PostMapping("{id}/products")
    public ResponseEntity<ProductDto> addProduct(@PathVariable Long id,
                                                 @Valid @RequestBody ProductDto productDto){
        log.info("Adding a new product to restaurant id {}", id);
        Product product = this.restaurantService.addProduct(id, productMapper.productDtoToProduct(productDto));
        log.info("Product {} created", product.getId());
        return new ResponseEntity<>(productMapper.productToProductDto(product), HttpStatus.CREATED);
    }

    @GetMapping("{id}/products")
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable Long id){
        log.info("Retrieving list of products for restaurant id '{}'", id);
        List<Product> products = this.productRepository.findAllByRestaurantId(id);
        log.info("{} products found", products.size());
        return new ResponseEntity<>(productMapper.listProductToListProductDto(products), HttpStatus.OK);
    }
}
