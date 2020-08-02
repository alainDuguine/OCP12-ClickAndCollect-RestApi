package org.clickandcollect.webservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RestaurantFullDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RestaurantDto restaurant;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<ProductDto> products;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<MenuDto> menus;
}
