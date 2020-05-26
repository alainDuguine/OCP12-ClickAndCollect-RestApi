package org.clickandcollect.webservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class CategoriesDto {
    private List<CategoryDto> categories;
}
