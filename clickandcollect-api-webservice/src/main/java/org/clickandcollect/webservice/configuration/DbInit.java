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
        if (categoryRepository.count() == 0) {
            List<Category> categories = new ArrayList<>();
            categories.add(Category.builder().name("Entrée").build());
            categories.add(Category.builder().name("Plat").build());
            categories.add(Category.builder().name("Dessert").build());
            categories.add(Category.builder().name("Boisson").build());
            categories.add(Category.builder().name("Formule").build());
            this.categoryRepository.saveAll(categories);
            this.categoryRepository.findAll().forEach(el -> log.info(el.getName()));

            Restaurant restaurant = this.restaurantRepository.save(Restaurant.builder().build());
            Category entree = this.categoryRepository.findCategoryByName("Entrée").get();
            Category plat = this.categoryRepository.findCategoryByName("Plat").get();
            Category dessert = this.categoryRepository.findCategoryByName("Dessert").get();

            List<Product> products = new ArrayList<>();
            products.add(Product.builder().name("Flan de courgettes").category(entree).description("Un délicieux flan de courgettes pour une entrée fraîche, végétarienne et sans gluten !").imageUrl("https://assets.afcdn.com/recipe/20190529/93192_w600cxt0cyt0cxb5760cyb3840.jpg").price(5.5).restaurant(restaurant).build());
            products.add(Product.builder().name("Salade César").category(entree).description("La traditionelle salade césar, avec laitue, parmesan, câpres, un classique !").imageUrl("https://assets.afcdn.com/recipe/20190704/94706_w600cxt0cyt0cxb4256cyb2832.jpg").price(7D).restaurant(restaurant).build());
            products.add(Product.builder().name("Tzatziki").category(entree).price(5D).description("Un concentré de fraîcheur et d'exotisme grâce au yaourt grec, concombre et son bouquet de menthe et de coriandre frais").imageUrl("https://assets.afcdn.com/recipe/20150814/642_w600.jpg").restaurant(restaurant).build());
            products.add(Product.builder().name("Lasagnes à la bolognaise").category(plat).price(12.5).description("Une délicieuse portion de lasagnes maison, à l'italienne, où le crémeux est amené par la mozzarella, et sans béchamel !").imageUrl("https://assets.afcdn.com/recipe/20160630/63224_w600.jpg").restaurant(restaurant).build());
            products.add(Product.builder().name("Poulet rôti et ses pommes de terre").category(plat).description("Essayez notre délicieux poulet fermier, rôti à la broche, aux herbes, avec ses pommes de terres").price(14.0).imageUrl("https://assets.afcdn.com/recipe/20140112/13508_w600.jpg").restaurant(restaurant).build());
            products.add(Product.builder().name("Pavé de saumon, sauce à l'aneth").category(plat).description("Un délicat pavé de saumon grillé à l'unilatérale, avec une subtile sauce à l'aneth relevée au citron").category(plat).imageUrl("https://assets.afcdn.com/recipe/20160922/1016_w1024h768c1cx1920cy1920.jpg").restaurant(restaurant).price(16.0).build());
            products.add(Product.builder().name("Crumble aux pommes").category(dessert).description("Un crumble fondant aux pommes, avec une pointe de cannelle").category(dessert).imageUrl("https://assets.afcdn.com/recipe/20130123/50045_w600.jpg").restaurant(restaurant).price(6.5).build());
            products.add(Product.builder().name("Mousse au chocolat maison").category(plat).description("Une mousse légère et aérienne, un nuage de délicieux chocolat Valrhona").category(dessert).imageUrl("https://assets.afcdn.com/recipe/20160624/25618_w600.jpg").restaurant(restaurant).price(7.0).build());
            products.add(Product.builder().name("Riz au lait").category(plat).description("Un riz au lait crémeux avec une délicate touche de vanille de Madagasscar").category(dessert).imageUrl("https://assets.afcdn.com/recipe/20171124/75539_w1024h768c1cx2760cy1886cxt0cyt0cxb5520cyb3773.jpg").restaurant(restaurant).price(5.5).build());

            this.productRepository.saveAll(products);
        }
    }
}
