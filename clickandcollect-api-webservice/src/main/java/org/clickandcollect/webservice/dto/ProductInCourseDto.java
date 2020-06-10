package org.clickandcollect.webservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductInCourseDto {
    @JsonIgnore
    private Long id;
    @NotNull
    private Long productId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private ProductDtoLight product;
    @Min(0)
    private Double extraCost;
}
