package org.clickandcollect.webservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.clickandcollect.business.contract.RestaurantService;
import org.clickandcollect.consumer.repositories.ProductRepository;
import org.clickandcollect.model.entities.Category;
import org.clickandcollect.model.entities.Product;
import org.clickandcollect.webservice.dtos.CategoryDto;
import org.clickandcollect.webservice.dtos.ProductDto;
import org.clickandcollect.webservice.mappers.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class RestaurantsApiControllerTest {

    @Mock
    private RestaurantService restaurantService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;
    @InjectMocks
    private RestaurantsApiController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void givenValidForm_whenAddProduct_ThenShouldPersist() throws Exception {
        ProductDto productDto = ProductDto.builder().id(1L).name("Test produit n°1").price(10.5D).category(new CategoryDto("Entrée")).build();
        Product product = Product.builder().id(1L).name("Test produit n°1").price(10.5D).category(new Category("Entrée")).build();

        given(restaurantService.addProduct(any(),any())).willReturn(product);
        given(productMapper.productToProductDto(any())).willReturn(productDto);

        mockMvc.perform(post("/restaurants/1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productDto)))
            .andExpect(status().isCreated());

        then(restaurantService).should().addProduct(any(),any());
    }

    @Test
    void givenInvalidFrom_whenAddProduct_ThenShouldThrowException() throws Exception {
        // productDto miss name, category and have negative value for price which is forbidden by validations annotations
        ProductDto productDto = ProductDto.builder().id(1L).price(-10.5D).build();

        MvcResult mvcResult = mockMvc.perform(post("/restaurants/1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(productDto)))
                .andExpect(status().isBadRequest())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

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
