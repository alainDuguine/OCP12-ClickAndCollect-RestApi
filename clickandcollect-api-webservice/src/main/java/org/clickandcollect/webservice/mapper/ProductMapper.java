package org.clickandcollect.webservice.mapper;

import org.clickandcollect.model.entitie.Product;
import org.clickandcollect.webservice.dto.ProductDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "restaurantId", source = "restaurant.id")
    @Mapping(target = "category", source = "category.name")
    ProductDto productToProductDto(Product product);

    @InheritInverseConfiguration
    Product productDtoToProduct(ProductDto productDto);

    List<ProductDto> listProductToListProductDto(List<Product> products);

}
