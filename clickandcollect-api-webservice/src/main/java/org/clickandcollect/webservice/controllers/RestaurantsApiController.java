package org.clickandcollect.webservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.RestaurantService;
import org.clickandcollect.consumer.repositories.ProductRepository;
import org.clickandcollect.model.entities.Product;
import org.clickandcollect.webservice.dtos.ProductDto;
import org.clickandcollect.webservice.mappers.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

    @PostMapping("{restaurantId}/products")
    public ResponseEntity<ProductDto> addProduct(@PathVariable Long restaurantId,
                                                 @Valid @RequestBody ProductDto productDto) {
        log.info("Adding a new product to restaurant id '{}'", restaurantId);
        Product product = this.restaurantService.addProduct(restaurantId, this.productMapper.productDtoToProduct(productDto));
        log.info("Product '{}' created", product.getId());
        return new ResponseEntity<>(this.productMapper.productToProductDto(product), HttpStatus.CREATED);
    }

    @GetMapping("{restaurantId}/products")
    public ResponseEntity<List<ProductDto>> getProducts(@PathVariable Long restaurantId) {
        log.info("Retrieving list of products for restaurant id '{}'", restaurantId);
        List<Product> products = this.productRepository.findAllByRestaurantId(restaurantId);
        log.info("{} products found", products.size());
        return new ResponseEntity<>(this.productMapper.listProductToListProductDto(products), HttpStatus.OK);
    }

    @PutMapping("{restaurantId}/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long restaurantId,
                                                    @PathVariable Long productId,
                                                    @Valid @RequestBody ProductDto productDto) {
        log.info("Updating product id '{}' for restaurant id '{}'", productId, restaurantId);
        Product product = this.restaurantService.updateProduct(
                productId,
                restaurantId,
                this.productMapper.productDtoToProduct(productDto)
        );
        log.info("Product '{}' updated", productId);
        return new ResponseEntity<>(this.productMapper.productToProductDto(product), HttpStatus.OK);
    }

    @DeleteMapping("{restaurantId}/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long restaurantId,
                                              @PathVariable Long productId) {
        log.info("Delete product id '{}' for restaurant id '{}'", productId, restaurantId);
        this.restaurantService.deleteProduct(productId, restaurantId);
        log.info("Product '{}' deleted", productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
