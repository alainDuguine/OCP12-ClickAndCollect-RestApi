package org.clickandcollect.business.impl;

import org.clickandcollect.business.exceptions.ResourceDuplicationException;
import org.clickandcollect.business.exceptions.UnknownResourceException;
import org.clickandcollect.consumer.repositories.CategoryRepository;
import org.clickandcollect.consumer.repositories.ProductRepository;
import org.clickandcollect.consumer.repositories.RestaurantRepository;
import org.clickandcollect.model.entities.Category;
import org.clickandcollect.model.entities.Product;
import org.clickandcollect.model.entities.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

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

    @Test()
    void addProduct_InvalidRestaurantId_ThrowsException(){
        given(this.restaurantRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(UnknownResourceException.class,
                () -> this.restaurantService.saveProduct(1L, Product.builder().build()));
    }

    @Test()
    void addProduct_InvalidCategoryName_ThrowsException(){
        given(this.restaurantRepository.findById(anyLong())).willReturn(Optional.of(this.restaurant));
        given(this.categoryRepository.findCategoryByName(anyString())).willReturn(Optional.empty());

        assertThrows(UnknownResourceException.class,
                () -> restaurantService.saveProduct(1L, product));
    }

    @Test()
    void addProduct_DuplicateUniqueName_ThrowsException(){
        given(this.restaurantRepository.findById(anyLong())).willReturn(Optional.of(this.restaurant));
        given(this.categoryRepository.findCategoryByName(anyString())).willReturn(Optional.of(this.category));
        given(this.productRepository.save(any())).willThrow(DataIntegrityViolationException.class);

        assertThrows(ResourceDuplicationException.class,
                () -> restaurantService.saveProduct(1L, product));
    }
}
