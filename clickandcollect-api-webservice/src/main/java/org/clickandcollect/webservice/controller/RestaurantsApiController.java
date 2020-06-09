package org.clickandcollect.webservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.RestaurantService;
import org.clickandcollect.model.entity.Product;
import org.clickandcollect.webservice.dto.ProductDto;
import org.clickandcollect.webservice.mapper.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = RestaurantsApiController.RESOURCE_URL)
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class RestaurantsApiController {

    public static final String RESOURCE_URL = "/restaurants/{restaurantId}";

    private final RestaurantService restaurantService;
    private final ProductMapper productMapper;

    public RestaurantsApiController(RestaurantService restaurantService, ProductMapper productMapper) {
        this.restaurantService = restaurantService;
        this.productMapper = productMapper;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(@RequestParam(value = "category", required = false) String category,
                                                        @PathVariable Long restaurantId) {
        log.info("Retrieving list of products for restaurant id '{}'", restaurantId);
        List<Product> products = this.restaurantService.findProductsByRestaurantId(restaurantId, category);
        log.info("{} products found", products.size());
        return new ResponseEntity<>(this.productMapper.listProductToListProductDto(products), HttpStatus.OK);
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long restaurantId,
                                                  @PathVariable Long productId) {
        log.info("Retrieving the product '{}' for restaurant id '{}'", productId, restaurantId);
        Product product = this.restaurantService.findProductByIds(restaurantId, productId);
        log.info("Product '{}' found", product.getId());
        return new ResponseEntity<>(this.productMapper.productToProductDto(product), HttpStatus.OK);
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDto> addProduct(@PathVariable Long restaurantId,
                                                 @Valid @RequestBody ProductDto productDto) {
        log.info("Adding a new product to restaurant id '{}'", restaurantId);
        Product product = this.restaurantService.saveProduct(restaurantId, this.productMapper.productDtoToProduct(productDto));
        log.info("Product '{}' created", product.getId());
        return new ResponseEntity<>(this.productMapper.productToProductDto(product), HttpStatus.CREATED);
    }

    @PutMapping("/products/{productId}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long restaurantId,
                                                    @PathVariable Long productId,
                                                    @Valid @RequestBody ProductDto productDto) {
        log.info("Updating product id '{}' for restaurant id '{}'", productId, restaurantId);
        Product product = this.restaurantService.updateProduct(
                restaurantId,
                productId,
                this.productMapper.productDtoToProduct(productDto)
        );
        log.info("Product '{}' updated", productId);
        return new ResponseEntity<>(this.productMapper.productToProductDto(product), HttpStatus.OK);
    }

    @DeleteMapping("/products/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long restaurantId,
                                              @PathVariable Long productId) {
        log.info("Delete product id '{}' for restaurant id '{}'", productId, restaurantId);
        this.restaurantService.deleteProduct(restaurantId, productId);
        log.info("Product '{}' deleted", productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
