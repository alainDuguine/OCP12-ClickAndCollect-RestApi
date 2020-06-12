package org.clickandcollect.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.clickandcollect.business.contract.MenuService;
import org.clickandcollect.model.entity.Menu;
import org.clickandcollect.webservice.dto.MenuCourseDto;
import org.clickandcollect.webservice.dto.MenuDto;
import org.clickandcollect.webservice.dto.ProductInCourseDto;
import org.clickandcollect.webservice.mapper.MenuMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hamcrest.Matchers.hasEntry;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MenusApiControllerIT {

    @Mock
    private MenuService menuService;
    @Mock
    private MenuMapper menuMapper;
    @InjectMocks
    private MenusApiController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new ExceptionControllerAdvice())
                .build();
    }

    @Test
    void givenValidMenu_whenAddMenu_ThenShouldPersist() throws Exception {
        MenuDto menuDto = this.getMenuDto();
        Menu menu = Menu.builder().build();

        given(menuService.saveMenu(any(),any())).willReturn(menu);

        mockMvc.perform(post("/restaurants/1/menus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(menuDto)))
                .andExpect(status().isCreated());

        then(menuService).should().saveMenu(any(),any());
    }

    @Test
    void givenInvalidForm_whenAddMenu_ThenShouldThrowException() throws Exception {
        // menuDto without name, menuCourses and have negative price
        MenuDto menuDto = MenuDto.builder().price(-10.5D).build();

        mockMvc.perform(post("/restaurants/1/menus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(menuDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.price").exists())
                .andExpect(jsonPath("$.errors.name").exists())
                .andExpect(jsonPath("$.errors.menuCourses").exists());

        then(menuService).shouldHaveNoInteractions();
        then(menuMapper).shouldHaveNoInteractions();
    }

    @Test
    void givenFormWithEmptyMenuCourse_whenAddMenu_ThenShouldThrowException() throws Exception {
        // menuDto with empty menuCourses
        MenuDto menuDto = MenuDto.builder()
                .name("Test invalid nested entities")
                .menuCourse(MenuCourseDto.builder().build())
                .price(10.5D)
                .build();

        mockMvc.perform(post("/restaurants/1/menus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(menuDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasEntry("menuCourses[0].productsInCourse","must not be null")))
                .andExpect(jsonPath("errors", hasEntry("menuCourses[0].category", "must not be null")));

        then(menuService).shouldHaveNoInteractions();
        then(menuMapper).shouldHaveNoInteractions();
    }

    @Test
    void givenFormWithInvalidProductInCourse_whenAddMenu_ThenShouldThrowException() throws Exception {
        // menuDto with empty product in productInCourse
        MenuDto menuDto = MenuDto.builder()
                .name("Test invalid nested entities")
                .menuCourse(MenuCourseDto
                        .builder()
                        .category("Entrée")
                        .productInCourse(ProductInCourseDto.builder().productId(null).build())
                        .build())
                .price(10.5D)
                .build();

        mockMvc.perform(post("/restaurants/1/menus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(menuDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("errors", hasEntry("menuCourses[0].productsInCourse[0].productId","must not be null")));


        then(menuService).shouldHaveNoInteractions();
        then(menuMapper).shouldHaveNoInteractions();
    }

    private MenuDto getMenuDto() {
        ProductInCourseDto productsInCourse = ProductInCourseDto.builder().productId(1L).build();
        MenuCourseDto menuCourse = MenuCourseDto.builder().category("Entrée").productInCourse(productsInCourse).build();
        return MenuDto.builder()
                .name("Test produit n°1")
                .price(10.5D)
                .description("Description Test produit n°1")
                .menuCourses(Collections.singletonList(menuCourse))
                .build();
    }

    private String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
