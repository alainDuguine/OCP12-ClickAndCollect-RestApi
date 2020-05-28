package org.clickandcollect.webservice.configuration;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.consumer.repositories.CategoryRepository;
import org.clickandcollect.consumer.repositories.ProductRepository;
import org.clickandcollect.consumer.repositories.RestaurantRepository;
import org.clickandcollect.model.entities.Category;
import org.clickandcollect.model.entities.Product;
import org.clickandcollect.model.entities.Restaurant;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DbInit implements CommandLineRunner {
    // TODO this class is provided for database test purpose
    private final CategoryRepository categoryRepository;
    private final RestaurantRepository restaurantRepository;
    private final ProductRepository productRepository;

    public DbInit(CategoryRepository categoryRepository, RestaurantRepository restaurantRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.restaurantRepository = restaurantRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Category> categories = new ArrayList<>();
        categories.add(Category.builder().name("Entrée").build());
        categories.add(Category.builder().name("Plat").build());
        categories.add(Category.builder().name("Dessert").build());
        categories.add(Category.builder().name("Boisson").build());
        categories.add(Category.builder().name("Formule").build());
        this.categoryRepository.saveAll(categories);
        this.categoryRepository.findAll().forEach(el -> log.info(el.getName()));

        Restaurant restaurant = this.restaurantRepository.save(Restaurant.builder().build());
        Category entrée = this.categoryRepository.findCategoryByName("Entrée").get();
        Category plat = this.categoryRepository.findCategoryByName("Plat").get();

        List<Product> products = new ArrayList<>();
        products.add(Product.builder().name("Entrée 1").category(entrée).price(5.5).restaurant(restaurant).build());
        products.add(Product.builder().name("Entrée 2").category(entrée).price(6.3).restaurant(restaurant).build());
        products.add(Product.builder().name("Entrée 3").category(entrée).price(4.8).restaurant(restaurant).build());
        products.add(Product.builder().name("Plat 1").category(plat).price(10.6).restaurant(restaurant).build());
        products.add(Product.builder().name("Plat 2").category(plat).price(15.0).restaurant(restaurant).build());
        this.productRepository.saveAll(products);
    }
}
