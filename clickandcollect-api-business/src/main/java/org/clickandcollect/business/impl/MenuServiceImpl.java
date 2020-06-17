package org.clickandcollect.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.MenuService;
import org.clickandcollect.business.exception.UnknownResourceException;
import org.clickandcollect.consumer.repository.CategoryRepository;
import org.clickandcollect.consumer.repository.MenuRepository;
import org.clickandcollect.consumer.repository.ProductRepository;
import org.clickandcollect.consumer.repository.RestaurantRepository;
import org.clickandcollect.model.entity.Category;
import org.clickandcollect.model.entity.Menu;
import org.clickandcollect.model.entity.MenuCourse;
import org.clickandcollect.model.entity.Product;
import org.clickandcollect.model.entity.ProductInCourse;
import org.clickandcollect.model.entity.Restaurant;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public MenuServiceImpl(MenuRepository menuRepository, RestaurantRepository restaurantRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Menu> findMenusByRestaurantId(Long restaurantId) {
        return this.menuRepository.findAllByRestaurantId(restaurantId);
    }

    @Override
    public Menu saveMenu(Long restaurantId, Menu menu) {
        Restaurant restaurant = this.restaurantRepository
                .findById(restaurantId)
                .orElseThrow(() ->
                        new UnknownResourceException("Unknown Restaurant '" + restaurantId + "'")
                );
        log.info("Restaurant id '{}' found", restaurant.getId());
        menu.setRestaurant(restaurant);

        for (int i = 0; i < menu.getMenuCourses().size(); i++) {
            MenuCourse menuCourse = menu.getMenuCourses().get(i);
            String categoryName = menuCourse.getCategory().getName();
            Category category = this.categoryRepository
                    .findCategoryByName(categoryName)
                    .orElseThrow(() ->
                            new UnknownResourceException("Unknown Category '" + categoryName + "'")
                    );
            log.info("Category name '{}' found", category);
            // creation des associations entre menuCourse et menu
            menuCourse.setMenu(menu);
            menuCourse.setCategory(category);
            menu.getMenuCourses().set(i, menuCourse);

            for (int j = 0; j < menuCourse.getProductsInCourse().size(); j++) {
                ProductInCourse productInCourse = menuCourse.getProductsInCourse().get(j);
                Product product = this.productRepository
                        .findProductByIdAndRestaurantId(
                                productInCourse.getProduct().getId(),
                                restaurantId)
                        .orElseThrow(() ->
                                new UnknownResourceException("Unknown Product '" + productInCourse.getProduct().getId() + "'")
                        );
                log.info("Product id '{}' for restaurant '{}' found", product.getId(), restaurantId);
                // creation des associations entre productInCourse et MenuCourse
                productInCourse.setProduct(product);
                productInCourse.setMenuCourse(menuCourse);
                menuCourse.getProductsInCourse().set(j, productInCourse);
            }
        }
        return menuRepository.save(menu);
    }

    @Override
    public Menu updateMenu(Long restaurantId, Long menuId, Menu menu) {
        log.info("Retrieving menu id '{}' for restaurant '{}'", menuId, restaurantId);
        if (this.menuRepository.findMenuByIdAndRestaurantId(menuId, restaurantId).isPresent()) {
            log.info("Menu found");
            menu.setId(menuId);
            return this.saveMenu(restaurantId, menu);
        } else {
            log.info("Menu not found");
            throw new UnknownResourceException(this.getUnknownResourceErrorMessage(menuId, restaurantId));
        }
    }

    @Override
    public void deleteMenu(Long restaurantId, Long menuId) {
        log.info("Retrieving menu id '{}' for restaurant '{}'", menuId, restaurantId);
        Menu menu = this.menuRepository
                .findMenuByIdAndRestaurantId(menuId, restaurantId)
                .orElseThrow(() -> new UnknownResourceException(this.getUnknownResourceErrorMessage(menuId, restaurantId)));
        log.info("Deleting menu");
        this.menuRepository.delete(menu);
    }

    private String getUnknownResourceErrorMessage(Long menuId, Long restaurantId){
        return "Unknown menu '" + menuId + "' restaurant '" + restaurantId + "'";
    }
}
