package org.clickandcollect.webservice.mappers;

import org.clickandcollect.model.entities.Category;
import org.clickandcollect.webservice.dto.CategoryDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDto categoryToDto(Category category);
    List<CategoryDto> categoryListToDtoList(List<Category> categories);
}
