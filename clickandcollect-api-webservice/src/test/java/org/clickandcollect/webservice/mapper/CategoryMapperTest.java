package org.clickandcollect.webservice.mapper;

import org.clickandcollect.model.entity.Category;
import org.clickandcollect.webservice.dto.CategoryDto;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryMapperTest {

    CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

    @Test
    void givenCategoryDto_whenMappingToCategoryModel_thenGetValidObject() {
        Category category = Category.builder()
                .id(1L)
                .name("Entrée")
                .build();

        CategoryDto categoryDto = this.categoryMapper.categoryToDto(category);

        assertThat(categoryDto.getId()).isEqualTo(category.getId());
        assertThat(categoryDto.getName()).isEqualTo(category.getName());
    }
}
