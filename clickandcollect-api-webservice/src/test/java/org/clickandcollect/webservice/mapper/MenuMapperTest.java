package org.clickandcollect.webservice.mapper;


import org.clickandcollect.model.entity.Category;
import org.clickandcollect.model.entity.Menu;
import org.clickandcollect.model.entity.MenuCourse;
import org.clickandcollect.model.entity.Product;
import org.clickandcollect.model.entity.ProductInCourse;
import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.dto.MenuCourseDto;
import org.clickandcollect.webservice.dto.MenuDto;
import org.clickandcollect.webservice.dto.ProductDtoLight;
import org.clickandcollect.webservice.dto.ProductInCourseDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class MenuMapperTest {

    MenuMapper menuMapper = Mappers.getMapper(MenuMapper.class);

    @Test
    void givenMenuModel_whenMappingToMenuDto_thenGetValidObject() {
        Category category = Category.builder()
                .id(1L)
                .name("Entrée")
                .build();

        Restaurant restaurant = Restaurant.builder()
                .id(1L)
                .name("Test restaurant")
                .email("em@ail.com")
                .password("password")
                .build();

        Product product = Product.builder()
                .id(1L)
                .name("Test product")
                .description("Description test for product")
                .price(10D)
                .imageUrl("http:://www.image.com")
                .category(category)
                .restaurant(restaurant)
                .build();

        ProductInCourse productInCourse1 = ProductInCourse.builder()
                .id(1L)
                .product(product)
                .extraCost(0D)
                .build();

        ProductInCourse productInCourse2 = ProductInCourse.builder()
                .id(2L)
                .product(product)
                .extraCost(1D)
                .build();

        MenuCourse course1 = MenuCourse.builder()
                .id(1L)
                .category(category)
                .productsInCourse(Arrays.asList(productInCourse1, productInCourse2))
                .build();

        MenuCourse course2 = MenuCourse.builder()
                .id(2L)
                .category(category)
                .productsInCourse(Arrays.asList(productInCourse1, productInCourse2))
                .build();

        Menu menu = Menu.builder()
                .id(1L)
                .name("Test menu")
                .description("Description test menu")
                .price(15D)
                .restaurant(restaurant)
                .menuCourses(Arrays.asList(course1,course2))
                .build();

        MenuDto menuDto = this.menuMapper.menuToDto(menu);
        MenuCourseDto menuCourseDto = menuDto.getMenuCourses().get(0);
        ProductInCourseDto productInCourseDto = menuCourseDto.getProductsInCourse().get(0);
        ProductDtoLight productDtoLight = productInCourseDto.getProduct();

        assertThat(menuDto.getId()).isEqualTo(menu.getId());
        assertThat(menuDto.getName()).isEqualTo(menu.getName());
        assertThat(menuDto.getDescription()).isEqualTo(menu.getDescription());
        assertThat(menuDto.getPrice()).isEqualTo(menu.getPrice());
        assertThat(menuDto.getRestaurantId()).isEqualTo(menu.getRestaurant().getId());

        assertThat(menuCourseDto.getId()).isEqualTo(course1.getId());
        assertThat(menuCourseDto.getCategory()).isEqualTo(course1.getCategory().getName());

        assertThat(productInCourseDto.getId()).isEqualTo(productInCourse1.getId());
        assertThat(productInCourseDto.getExtraCost()).isEqualTo(productInCourse1.getExtraCost());

        assertThat(productDtoLight.getId()).isEqualTo(product.getId());
        assertThat(productDtoLight.getName()).isEqualTo(product.getName());
        assertThat(productDtoLight.getDescription()).isEqualTo(product.getDescription());
        assertThat(productDtoLight.getImageUrl()).isEqualTo(product.getImageUrl());
    }

    @Test
    void givenMenuDto_whenMappingToMenuModel_thenGetValidObject() {

        ProductInCourseDto productInCourse1 = ProductInCourseDto.builder()
                .productId(1L)
                .extraCost(0D)
                .build();

        ProductInCourseDto productInCourse2 = ProductInCourseDto.builder()
                .productId(2L)
                .extraCost(1D)
                .build();

        MenuCourseDto course1 = MenuCourseDto.builder()
                .id(1L)
                .category("Entrée")
                .productsInCourse(Arrays.asList(productInCourse1, productInCourse2))
                .build();

        MenuCourseDto course2 = MenuCourseDto.builder()
                .category("Plat")
                .productsInCourse(Arrays.asList(productInCourse1, productInCourse2))
                .build();

        MenuDto menuDto = MenuDto.builder()
                .name("Test menu")
                .description("Description test menu")
                .price(15D)
                .menuCourses(Arrays.asList(course1,course2))
                .build();

        Menu menu = this.menuMapper.menuDtoToMenu(menuDto);
        MenuCourse menuCourse = menu.getMenuCourses().get(0);
        ProductInCourse productInCourse = menuCourse.getProductsInCourse().get(0);
        Product product = productInCourse.getProduct();

        assertThat(menu.getName()).isEqualTo(menuDto.getName());
        assertThat(menu.getDescription()).isEqualTo(menuDto.getDescription());
        assertThat(menu.getPrice()).isEqualTo(menuDto.getPrice());

        assertThat(menuCourse.getCategory().getName()).isEqualTo(course1.getCategory());

        assertThat(productInCourse.getId()).isEqualTo(productInCourse1.getId());
        assertThat(productInCourse.getExtraCost()).isEqualTo(productInCourse1.getExtraCost());

        assertThat(product.getId()).isEqualTo(productInCourse1.getProductId());
    }


}
