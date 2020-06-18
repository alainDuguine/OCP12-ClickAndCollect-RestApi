package org.clickandcollect.webservice.controller;

import org.clickandcollect.business.contract.AuthenticationService;
import org.clickandcollect.business.exception.UnknownResourceException;
import org.clickandcollect.webservice.mapper.RestaurantMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.head;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AuthenticationApiControllerIT {

    @Mock
    private AuthenticationService authenticationService;
    @Mock
    private RestaurantMapper restaurantMapper;
    @InjectMocks
    private AuthenticationApiController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setControllerAdvice(new ExceptionControllerAdvice())
                .build();
    }

    @Test
    void givenExistingEmail_whenCheckEmailExists_ThenShouldGetCode200() throws Exception {
        mockMvc.perform(head("/auth/register")
                .param("email", "em@ail.com"))
                .andExpect(status().isOk());
    }

    @Test
    void givenUnknownEmail_whenCheckEmailExists_ThenShouldGetCode404() throws Exception {
        doThrow(new UnknownResourceException("")).when(this.authenticationService).checkEmailExists(anyString());

        mockMvc.perform(head("/auth/register")
                .param("email", "em@ail.com"))
                .andExpect(status().isNotFound());
    }
}
