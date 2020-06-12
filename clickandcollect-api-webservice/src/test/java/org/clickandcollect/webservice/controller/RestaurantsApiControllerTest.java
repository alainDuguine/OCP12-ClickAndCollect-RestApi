package org.clickandcollect.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.clickandcollect.business.contract.RestaurantService;
import org.clickandcollect.model.entity.Category;
import org.clickandcollect.model.entity.Product;
import org.clickandcollect.webservice.dto.ProductDto;
import org.clickandcollect.webservice.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RestaurantsApiControllerTest {

    @Mock
    private RestaurantService restaurantService;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private RestaurantsApiController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new ExceptionControllerAdvice())
                .build();
    }

    @Test
    void givenValidForm_whenAddProduct_ThenShouldPersist() throws Exception {
        ProductDto productDto = ProductDto.builder().id(1L).name("Test produit n°1").price(10.5D).category("Entrée").build();
        Product product = Product.builder().id(1L).name("Test produit n°1").price(10.5D).category(new Category("Entrée")).build();

        given(restaurantService.saveProduct(any(),any())).willReturn(product);
        given(productMapper.productToProductDto(any())).willReturn(productDto);

        mockMvc.perform(post("/restaurants/1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productDto)))
            .andExpect(status().isCreated());

        then(restaurantService).should().saveProduct(any(),any());
    }

    @Test
    void givenInvalidForm_whenAddProduct_ThenShouldThrowException() throws Exception {
        // productDto miss name, category and have negative value for price which is forbidden by validations annotations
        ProductDto productDto = ProductDto.builder().id(1L).price(-10.5D).build();

        mockMvc.perform(post("/restaurants/1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors.price").exists())
                .andExpect(jsonPath("errors.name").exists())
                .andExpect(jsonPath("errors.category").exists());

        then(restaurantService).shouldHaveNoInteractions();
        then(productMapper).shouldHaveNoInteractions();
    }


    @Test
    void givenValidForm_whenUpdateProduct_ThenShouldPersist() throws Exception {
        ProductDto productDto = ProductDto.builder().id(1L).name("Test produit n°1").price(10.5D).category("Entrée").build();
        Product product = Product.builder().id(1L).name("Test produit n°1").price(10.5D).category(new Category("Entrée")).build();

        given(restaurantService.updateProduct(1L, productDto.getId(), product)).willReturn(product);
        given(productMapper.productDtoToProduct(any())).willReturn(product);

        mockMvc.perform(put("/restaurants/1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productDto)))
                .andExpect(status().isOk());

        then(restaurantService).should().updateProduct(1L, product.getId(), product);
    }

    @Test
    void givenInvalidFrom_whenPutProduct_ThenShouldThrowException() throws Exception {
        // productDto miss name, category and have negative value for price which is forbidden by validations annotations
        ProductDto productDto = ProductDto.builder().id(1L).price(-10.5D).build();

        mockMvc.perform(put("/restaurants/1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors.price").exists())
                .andExpect(jsonPath("errors.name").exists())
                .andExpect(jsonPath("errors.category").exists());

        then(restaurantService).shouldHaveNoInteractions();
        then(productMapper).shouldHaveNoInteractions();
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
