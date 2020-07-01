package org.clickandcollect.webservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MenuDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @NotNull
    @Size(max = 100)
    private String name;
    @Size(max = 255)
    private String description;
    @Min(0)
    @NotNull
    private Double price;
    @Size(min = 1, max = 10)
    @Singular
    @Valid
    List<MenuCourseDto> menuCourses = new ArrayList<>();
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long restaurantId;

}
