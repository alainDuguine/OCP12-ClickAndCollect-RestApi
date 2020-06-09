package org.clickandcollect.consumer.repository;

import org.clickandcollect.model.entity.Category;
import org.clickandcollect.model.entity.Product;
import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.ClickAndCollectApiApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(classes = ClickAndCollectApiApplication.class)
@TestPropertySource(locations = {"classpath:/application-test.properties"})
class ProductRepositoryIT {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        this.product = Product.builder()
                .name("Test product")
                .price(12.56)
                .restaurant(
                        Restaurant.builder().id(1L).build())
                .category(
                        Category.builder().id(1L).build()
                )
                .build();

    }

    @AfterEach
    void tearDown() {
        this.productRepository.delete(this.product);
    }

    @Test
    void givenInvalidProduct_whenAddNewProduct_shouldNotBePersistedToDatabase(){
        long nbProducts = this.productRepository.count();

        this.product.setName(null);

        assertThrows(ConstraintViolationException.class, () -> this.productRepository.save(product));
        assertThat(this.productRepository.count()).isEqualTo(nbProducts);
    }

    @Test
    void givenDuplicateProductName_whenAddNewProduct_shouldNotBePersistedToDatabase(){
        long nbProducts = this.productRepository.count();

        this.productRepository.save(product);

        assertThat(this.productRepository.count()).isEqualTo(nbProducts + 1);

        this.product.setId(null);

        assertThrows(DataIntegrityViolationException.class, () -> this.productRepository.save(product));
        assertThat(this.productRepository.count()).isEqualTo(nbProducts + 1);
    }

    @Test
    void givenDuplicateProductNameWithDifferentRestaurantId_whenAddNewProduct_shouldBePersistedToDatabase(){
        Restaurant restaurant = this.restaurantRepository.save(Restaurant.builder().id(2L).build());

        long nbProducts = this.productRepository.count();

        this.product.setId(null);
        this.product.setRestaurant(restaurant);
        this.productRepository.save(product);

        assertThat(this.productRepository.count()).isEqualTo(nbProducts + 1);
    }

    @Test
    void givenUnknownRestaurantId_whenGetProducts_shouldReturnEmptyList() {
        assertThat(this.productRepository.findAllByRestaurantId(9999L).size()).isEqualTo(0);
    }

    @Test
    void givenExistingRestaurantId_whenGetProducts_shouldReturnNotEmptyList() {
        assertThat(this.productRepository.findAllByRestaurantId(1L).size()).isGreaterThan(0);
    }

    @Test
    void givenUnknownProductId_whenGetProduct_shouldReturnOptionalEmpty() {
        Optional<Product> product = this.productRepository.findProductByIdAndRestaurantId(1L,1L);
        assertThat(product.isPresent()).isTrue();
    }

    @Test
    void givenExistingProductId_whenGetProduct_shouldReturnProduct() {
        Optional<Product> product = this.productRepository.findProductByIdAndRestaurantId(9999L,1L);
        assertThat(product.isEmpty()).isTrue();
    }

    @Test
    void givenUnknownCategory_whenGetProducts_shouldReturnEmptyList() {
        assertThat(this.productRepository.findAllByRestaurantIdAndCategoryName(1L, "test").size()).isEqualTo(0);
    }

    @Test
    void givenExistingCategory_whenGetProducts_shouldReturnNotEmptyList() {
        assertThat(this.productRepository.findAllByRestaurantIdAndCategoryName(1L, "Plat").size()).isGreaterThan(0);
    }

    @Test
    void givenNoCategory_whenGetProducts_shouldReturnFullList() {
        long nbItems = productRepository.count();
        assertThat(this.productRepository.findAllByRestaurantIdAndCategoryName(1L, null).size()).isEqualTo(nbItems);
    }

}
