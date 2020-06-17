package org.clickandcollect.webservice.mapper;

import org.clickandcollect.model.entity.Category;
import org.clickandcollect.model.entity.Product;
import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.dto.ProductDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class ProductMapperTest {

    ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void givenProductDto_whenMappingToProductModel_thenGetValidObject() {
        ProductDto productDto = ProductDto.builder()
                .id(1L)
                .name("Test product")
                .description("Description test for product")
                .price(10D)
                .imageUrl("http:://www.image.com")
                .category("Entrée")
                .restaurantId(1L)
                .build();

        Product product = this.productMapper.productDtoToProduct(productDto);

        assertThat(product.getId()).isEqualTo(productDto.getId());
        assertThat(product.getName()).isEqualTo(productDto.getName());
        assertThat(product.getDescription()).isEqualTo(productDto.getDescription());
        assertThat(product.getPrice()).isEqualTo(productDto.getPrice());
        assertThat(product.getImageUrl()).isEqualTo(productDto.getImageUrl());
        assertThat(product.getCategory().getName()).isEqualTo(productDto.getCategory());
        assertThat(product.getRestaurant().getId()).isEqualTo(productDto.getRestaurantId());
    }

    @Test
    void givenProductModel_whenMappingToProductDto_thenGetValidObject() {
        Product product = Product.builder()
                .id(1L)
                .name("Test product")
                .description("Description test for product")
                .price(10D)
                .imageUrl("http:://www.image.com")
                .category(Category.builder()
                        .id(1L)
                        .name("Entrée")
                        .build())
                .restaurant(Restaurant.builder()
                        .id(1L)
                        .name("Test restaurant")
                        .email("em@ail.com")
                        .password("password")
                        .build())
                .build();

        ProductDto productDto = this.productMapper.productToProductDto(product);

        assertThat(productDto.getId()).isEqualTo(product.getId());
        assertThat(productDto.getName()).isEqualTo(product.getName());
        assertThat(productDto.getDescription()).isEqualTo(product.getDescription());
        assertThat(productDto.getPrice()).isEqualTo(product.getPrice());
        assertThat(productDto.getImageUrl()).isEqualTo(product.getImageUrl());
        assertThat(productDto.getCategory()).isEqualTo(product.getCategory().getName());
        assertThat(productDto.getRestaurantId()).isEqualTo(product.getRestaurant().getId());
    }
}
