package org.clickandcollect.webservice.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AuthenticationApiControllerIT {

//    @Mock
//    private AuthenticationService authenticationService;
//    @Mock
//    private RestaurantMapper restaurantMapper;
//    @InjectMocks
//    private AuthenticationApiController controller;
//
//    MockMvc mockMvc;
//
//    @BeforeEach
//    void setUp() {
//        mockMvc = MockMvcBuilders
//                .standaloneSetup(controller)
//                .setControllerAdvice(new ExceptionControllerAdvice())
//                .build();
//    }
//
//    @Test
//    void givenExistingEmail_whenCheckEmailExists_ThenShouldGetCode200() throws Exception {
//        mockMvc.perform(head("/auth/register")
//                .param("email", "em@ail.com"))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void givenUnknownEmail_whenCheckEmailExists_ThenShouldGetCode404() throws Exception {
//        doThrow(new UnknownResourceException("")).when(this.authenticationService).checkEmailExists(anyString());
//
//        mockMvc.perform(head("/auth/register")
//                .param("email", "em@ail.com"))
//                .andExpect(status().isNotFound());
//    }
}
