package org.clickandcollect.consumer.repositories;

import org.clickandcollect.model.entities.Category;
import org.clickandcollect.model.entities.Product;
import org.clickandcollect.model.entities.Restaurant;
import org.clickandcollect.webservice.ClickAndCollectApiApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import javax.validation.ConstraintViolationException;

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

}
