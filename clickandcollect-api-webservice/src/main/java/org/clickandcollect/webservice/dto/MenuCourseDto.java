package org.clickandcollect.webservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuCourseDto {
    @JsonIgnore
    private Long id;
    @NotNull
    private String category;

    @NotNull
    @Size(min = 1, max = 50)
    @Singular("productInCourse")
    @Valid
    private List<ProductInCourseDto> productsInCourse;
}
