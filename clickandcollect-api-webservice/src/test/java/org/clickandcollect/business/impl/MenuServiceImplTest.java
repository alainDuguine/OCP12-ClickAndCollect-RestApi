package org.clickandcollect.business.impl;

import org.clickandcollect.business.exception.UnknownResourceException;
import org.clickandcollect.consumer.repository.CategoryRepository;
import org.clickandcollect.consumer.repository.MenuRepository;
import org.clickandcollect.consumer.repository.ProductRepository;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class MenuServiceImplTest {

    @Mock
    private MenuRepository menuRepository;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private MenuServiceImpl menuService;

    /*===================================
    == DELETE ===========================
    =====================================*/

    @Test()
    void givenUnknownRestaurantOrMenuId_whenDeleteMenu_thenThrowsException(){
        given(this.menuRepository.findMenuByIdAndRestaurantId(anyLong(), anyLong())).willReturn(Optional.empty());

        assertThrows(UnknownResourceException.class,
                () -> this.menuService.deleteMenu(1L, 1L));
    }

}
