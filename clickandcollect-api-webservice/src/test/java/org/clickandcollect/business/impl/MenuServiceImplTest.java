package org.clickandcollect.business.impl;

import org.clickandcollect.business.exception.UnknownResourceException;
import org.clickandcollect.consumer.repository.CategoryRepository;
import org.clickandcollect.consumer.repository.MenuRepository;
import org.clickandcollect.consumer.repository.ProductRepository;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.clickandcollect.model.entity.Category;
import org.clickandcollect.model.entity.Menu;
import org.clickandcollect.model.entity.MenuCourse;
import org.clickandcollect.model.entity.Product;
import org.clickandcollect.model.entity.ProductInCourse;
import org.clickandcollect.model.entity.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MenuServiceImplTest {

    @Mock
    private MenuRepository menuRepository;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private MenuServiceImpl menuService;
    private Restaurant restaurant;
    private Menu menu;

    @BeforeEach
    void setUp() {
        this.restaurant = Restaurant.builder().id(1L).build();
        this.menu = Menu.builder()
                .name("Test product")
                .price(12.56)
                .restaurant(
                        Restaurant.builder().id(1L).build())
                .build();

        MenuCourse menuCourse = MenuCourse.builder()
                .id(null)
                .category(Category.builder().name("category").build())
                .menu(this.menu)
                .build();

        ProductInCourse productInCourse = ProductInCourse.builder()
                .product(Product.builder().id(1L).build())
                .menuCourse(menuCourse)
                .build();

        menuCourse.setProductsInCourse(Arrays.asList(productInCourse));
        this.menu.setMenuCourses(Arrays.asList(menuCourse));
    }

    /*===================================
    == POST =============================
    =====================================*/

    @Test()
    void givenInvalidRestaurantId_whenAddProduct_thenThrowsException(){
        given(this.restaurantRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(UnknownResourceException.class,
                () -> this.menuService.saveMenu(1L, Menu.builder().build()));
    }

    @Test()
    void givenInvalidCategoryName_whenAddMenu_thenThrowsException(){
        given(this.restaurantRepository.findById(anyLong())).willReturn(Optional.of(this.restaurant));
        given(this.categoryRepository.findCategoryByName(anyString())).willReturn(Optional.empty());

        assertThrows(UnknownResourceException.class,
                () -> menuService.saveMenu(1L, menu));
    }

    @Test()
    void givenUnknownProduct_whenAddMenu_thenThrowsException(){
        given(this.restaurantRepository.findById(anyLong())).willReturn(Optional.of(this.restaurant));
        given(this.categoryRepository.findCategoryByName(any())).willReturn(Optional.of(Category.builder().build()));
        given(this.productRepository.findProductByIdAndRestaurantId(any(),any())).willReturn(Optional.empty());

        assertThrows(UnknownResourceException.class,
                () -> menuService.saveMenu(1L, menu));
    }

    /*===================================
    == PUT ==============================
    =====================================*/

    @Test()
    void givenUnknownRestaurantOrMenuId_whenUpdateMenu_thenThrowsException(){
        given(this.menuRepository.findMenuByIdAndRestaurantId(anyLong(), anyLong())).willReturn(Optional.empty());

        assertThrows(UnknownResourceException.class,
                () -> this.menuService.updateMenu(0L, 0L, Menu.builder().build()));
    }

    @Test()
    void givenValidRestaurantAndMenuId_whenUpdateMenu_shouldCallSaveMenu(){
        given(this.menuRepository.findMenuByIdAndRestaurantId(anyLong(), anyLong())).willReturn(Optional.of(Menu.builder().build()));
        given(this.restaurantRepository.findById(anyLong())).willReturn(Optional.of(Restaurant.builder().build()));
        given(this.categoryRepository.findCategoryByName(any())).willReturn(Optional.of(Category.builder().build()));
        given(this.productRepository.findProductByIdAndRestaurantId(any(),any())).willReturn(Optional.of(Product.builder().build()));
        given(this.menuRepository.save(any())).willReturn(this.menu);

        menu = this.menuService.updateMenu(1L, 1L, this.menu);

        assertThat(menu.getId()).isEqualTo(1L);
        verify(this.menuRepository, times(1)).save(any());
    }

    /*===================================
    == DELETE ===========================
    =====================================*/

    @Test()
    void givenUnknownRestaurantOrMenuId_whenDeleteMenu_thenThrowsException(){
        given(this.menuRepository.findMenuByIdAndRestaurantId(anyLong(), anyLong())).willReturn(Optional.empty());

        assertThrows(UnknownResourceException.class,
                () -> this.menuService.deleteMenu(1L, 1L));
    }

}
