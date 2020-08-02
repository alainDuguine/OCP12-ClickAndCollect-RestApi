package org.clickandcollect.consumer.repository;

import org.clickandcollect.model.entity.Category;
import org.clickandcollect.model.entity.Menu;
import org.clickandcollect.model.entity.MenuCourse;
import org.clickandcollect.model.entity.Product;
import org.clickandcollect.model.entity.ProductInCourse;
import org.clickandcollect.model.entity.Restaurant;
import org.clickandcollect.webservice.ClickAndCollectApiApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.TestPropertySource;

import javax.validation.ConstraintViolationException;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = ClickAndCollectApiApplication.class)
@TestPropertySource(locations = {"classpath:/application-test.properties"})
public class MenuRepositoryIT {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    private Menu menu;

    @BeforeEach
    void setUp() {
        this.menu = Menu.builder()
                .name("Test product")
                .price(12.56)
                .restaurant(
                        Restaurant.builder().id(1L).build())
                .build();

        MenuCourse menuCourse = MenuCourse.builder()
                .id(null)
                .category(Category.builder().id(1L).build())
                .menu(this.menu)
                .build();

        ProductInCourse productInCourse = ProductInCourse.builder()
                .product(Product.builder().id(1L).build())
                .menuCourse(menuCourse)
                .build();

        this.menu.setMenuCourses(Collections.singletonList(menuCourse));
    }

    @Test
    void givenExistingRestaurantId_whenGetMenus_shouldReturnMenu(){
        assertThat(this.menuRepository.findAllByRestaurantId(1L).size()).isGreaterThan(0);
    }

    @Test
    void givenUnknownRestaurantId_whenGetMenus_shouldNotReturnMenu(){
        assertThat(this.menuRepository.findAllByRestaurantId(9999L).size()).isEqualTo(0);
    }

    @Test
    void givenInvalidMenu_whenAddNewMenu_shouldNotBePersistedToDatabase(){
        long nbMenus = this.menuRepository.count();

        this.menu.setName(null);

        assertThrows(ConstraintViolationException.class, () -> this.menuRepository.save(this.menu));
        assertThat(this.menuRepository.count()).isEqualTo(nbMenus);
    }

    @Test
    void givenDuplicateMenuName_whenAddNewMenu_shouldNotBePersistedToDatabase(){
        long nbMenu = this.menuRepository.count();

        this.menu = this.menuRepository.save(this.menu);

        assertThat(this.menuRepository.count()).isEqualTo(nbMenu + 1);

        Long id  = this.menu.getId();
        this.menu.setId(null);

        assertThrows(DataIntegrityViolationException.class, () -> this.menuRepository.save(menu));
        assertThat(this.menuRepository.count()).isEqualTo(nbMenu + 1);

        this.menuRepository.deleteById(id);
    }

    @Test
    void givenDuplicateMenuNameWithDifferentRestaurantId_whenAddNewMenu_shouldBePersistedToDatabase(){
        Restaurant restaurant = this.restaurantRepository.save(Restaurant.builder()
                .id(99L)
                .name("test Name")
                .password("password")
                .email("email@example.com").build());

        this.menu = this.menuRepository.save(this.menu);

        long nbMenus = this.menuRepository.count();

        Long id  = this.menu.getId();
        this.setUp();
        this.menu.setRestaurant(restaurant);
        this.menuRepository.save(menu);

        assertThat(this.menuRepository.count()).isEqualTo(nbMenus + 1);

        this.menuRepository.deleteById(id);
        this.menuRepository.deleteById(this.menu.getId());
        this.restaurantRepository.deleteById(restaurant.getId());
    }
}
