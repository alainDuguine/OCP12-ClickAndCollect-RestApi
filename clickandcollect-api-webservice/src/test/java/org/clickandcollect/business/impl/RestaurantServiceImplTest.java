package org.clickandcollect.business.impl;

import org.clickandcollect.business.exception.ResourceDuplicationException;
import org.clickandcollect.business.exception.UnknownResourceException;
import org.clickandcollect.consumer.repository.CategoryRepository;
import org.clickandcollect.consumer.repository.ProductRepository;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.clickandcollect.model.entity.Category;
import org.clickandcollect.model.entity.Product;
import org.clickandcollect.model.entity.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class RestaurantServiceImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private RestaurantServiceImpl restaurantService;

    Restaurant restaurant;
    Product product;
    Category category;

    @BeforeEach
    void setUp() {
        this.restaurant = Restaurant.builder().id(1L).build();
        this.category = Category.builder().id(1L).name("Entrée").build();
        this.product = Product.builder().name("Product test").category(category).price(10D).restaurant(restaurant).build();
    }

    /*===================================
    == GET ==============================
    =====================================*/

    @Test()
    void givenUnknownProductOrRestaurant_whenFindProductByIds_thenThrowsException() {
        given(this.productRepository.findProductByIdAndRestaurantId(anyLong(), anyLong())).willReturn(Optional.empty());

        assertThrows(UnknownResourceException.class,
                () -> this.restaurantService.findProductByIds(2L, 2L));
    }

    /*===================================
    == POST =============================
    =====================================*/

    @Test()
    void givenInvalidRestaurantId_whenAddProduct_thenThrowsException(){
        given(this.restaurantRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(UnknownResourceException.class,
                () -> this.restaurantService.saveProduct(1L, Product.builder().build()));
    }

    @Test()
    void givenInvalidCategoryName_whenAddProduct_thenThrowsException(){
        given(this.restaurantRepository.findById(anyLong())).willReturn(Optional.of(this.restaurant));
        given(this.categoryRepository.findCategoryByName(anyString())).willReturn(Optional.empty());

        assertThrows(UnknownResourceException.class,
                () -> restaurantService.saveProduct(1L, product));
    }

    @Test()
    void givenDuplicateUniqueName_whenAddProduct_thenThrowsException(){
        given(this.restaurantRepository.findById(anyLong())).willReturn(Optional.of(this.restaurant));
        given(this.categoryRepository.findCategoryByName(anyString())).willReturn(Optional.of(this.category));
        given(this.productRepository.save(any())).willThrow(DataIntegrityViolationException.class);

        assertThrows(ResourceDuplicationException.class,
                () -> restaurantService.saveProduct(1L, product));
    }

    /*===================================
    == PUT ==============================
    =====================================*/

    @Test()
    void givenUnknownRestaurantOrProductId_whenUpdateProduct_thenThrowsException(){
        given(this.productRepository.findProductByIdAndRestaurantId(anyLong(), anyLong())).willReturn(Optional.empty());

        assertThrows(UnknownResourceException.class,
                () -> this.restaurantService.updateProduct(1L, 1L, Product.builder().build()));
    }

    @Test()
    void givenValidRestaurantAndProductId_whenUpdateProduct_shouldCallSaveProduct(){
        given(this.productRepository.findProductByIdAndRestaurantId(anyLong(), anyLong())).willReturn(Optional.of(Product.builder().build()));
        given(this.restaurantRepository.findById(anyLong())).willReturn(Optional.of(Restaurant.builder().build()));
        given(this.categoryRepository.findCategoryByName(anyString())).willReturn(Optional.of(Category.builder().build()));
        given(this.productRepository.save(any())).willReturn(this.product);

        product = this.restaurantService.updateProduct(1L, 1L, this.product);

        assertThat(product.getId()).isEqualTo(1L);
    }

    /*===================================
    == DELETE ===========================
    =====================================*/

    @Test()
    void givenUnknownRestaurantOrProductId_whenDeleteProduct_thenThrowsException(){
        given(this.productRepository.findProductByIdAndRestaurantId(anyLong(), anyLong())).willReturn(Optional.empty());

        assertThrows(UnknownResourceException.class,
                () -> this.restaurantService.deleteProduct(1L, 1L));
    }
}
