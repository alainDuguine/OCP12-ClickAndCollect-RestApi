package org.clickandcollect.consumer.repositorie;

import org.clickandcollect.consumer.repository.ProductRepository;
import org.clickandcollect.model.entitie.Category;
import org.clickandcollect.model.entitie.Product;
import org.clickandcollect.model.entitie.Restaurant;
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
    void givenValidProduct_whenAddNewProduct_shouldPersistToDatabase(){
        long nbProducts = this.productRepository.count();

        this.productRepository.save(product);

        assertThat(this.productRepository.count()).isEqualTo(nbProducts + 1);
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

}
