package org.clickandcollect.webservice.mappers;

import org.clickandcollect.model.entities.Category;
import org.clickandcollect.model.entities.Product;
import org.clickandcollect.model.entities.Restaurant;
import org.clickandcollect.webservice.dtos.CategoryDto;
import org.clickandcollect.webservice.dtos.ProductDto;
import org.clickandcollect.webservice.dtos.RestaurantDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto productToProductDto(Product product);
    Product productDtoToProduct(ProductDto productDto);
    List<ProductDto> listProductToListProductDto(List<Product> products);
    CategoryDto categoryToDto(Category category);
    List<CategoryDto> categoryListToDtoList(List<Category> categories);
    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);
    Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto);
}
