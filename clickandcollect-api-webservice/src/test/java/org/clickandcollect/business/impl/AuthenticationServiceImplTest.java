package org.clickandcollect.business.impl;

import org.clickandcollect.business.exception.ResourceDuplicationException;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.clickandcollect.model.entity.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceImplTest {

    @Mock
    private RestaurantRepository restaurantRepository;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;
    private Restaurant restaurant;

    @BeforeEach
    void setUp() {
       restaurant = Restaurant.builder().email("em@il").name("test name").password("password").build();
    }

    @Test
    void givenValidObject_whenAddNewRestaurant_thenShouldPersist() {
        given(this.restaurantRepository.save(any())).willThrow(DataIntegrityViolationException.class);

        assertThrows(ResourceDuplicationException.class,
                () -> this.authenticationService.register(restaurant));
    }
}
