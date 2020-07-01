package org.clickandcollect.webservice.configuration;

import lombok.extern.slf4j.Slf4j;
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
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final MenuRepository menuRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public DbInit(CategoryRepository categoryRepository, RestaurantRepository restaurantRepository, ProductRepository productRepository, MenuRepository menuRepository, BCryptPasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.restaurantRepository = restaurantRepository;
        this.productRepository = productRepository;
        this.menuRepository = menuRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            List<Category> categories = new ArrayList<>();
            categories.add(Category.builder().name("Entrée").build());
            categories.add(Category.builder().name("Plat").build());
            categories.add(Category.builder().name("Dessert").build());
            categories.add(Category.builder().name("Boisson").build());
            categories.add(Category.builder().name("Formule").build());
            this.categoryRepository.saveAll(categories);
            this.categoryRepository.findAll().forEach(el -> log.info(el.getName()));

            Restaurant restaurant = Restaurant.builder()
                    .name("Chez Monique & Myrtille")
                    .email("em@il.com")
                    .password(passwordEncoder.encode("password"))
                    .description("La meilleure cantine du quartier Gambetta !")
                    .typeCuisine("Cuisine du jour fraîche et du marché")
                    .formattedAddress("83 Rue Orfila, 75020, Paris")
                    .latitude("48.868028")
                    .longitude("2.399884")
                    .roles("ROLE_USER")
                    .photo("C:\\Users\\alain\\Pictures\\ClickAndCollectPhoto\\1.jpg")
                    .build();

            restaurant = this.restaurantRepository.save(restaurant);
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

            List<Product> entrees = this.productRepository.findAllByRestaurantIdAndCategoryName(restaurant.getId(), entree.getName());
            List<Product> plats = this.productRepository.findAllByRestaurantIdAndCategoryName(restaurant.getId(), plat.getName());
            List<Product> desserts = this.productRepository.findAllByRestaurantIdAndCategoryName(restaurant.getId(), dessert.getName());

            Menu menu1 = Menu.builder()
                    .name("Menu Complet")
                    .description("Un menu avec une entrée, un plat et un dessert au choix")
                    .restaurant(restaurant)
                    .price(16D)
                    .build();

            MenuCourse entreeMenu = MenuCourse
                    .builder()
                    .category(entree)
                    .build();

            ProductInCourse entree1 = ProductInCourse
                    .builder()
                    .product(entrees.get(0))
                    .build();

            entreeMenu.addProductInCourse(entree1);

            entreeMenu.addProductInCourse(ProductInCourse
                    .builder()
                    .product(entrees.get(1))
                    .extraCost(1D)
                    .build());

            MenuCourse platMenu = MenuCourse
                    .builder()
                    .category(plat)
                    .build();

            platMenu.addProductInCourse(ProductInCourse
                    .builder()
                    .product(plats.get(0))
                    .build());

            platMenu.addProductInCourse(ProductInCourse
                    .builder()
                    .product(plats.get(1))
                    .extraCost(1D)
                    .build());

            MenuCourse dessertMenu = MenuCourse
                    .builder()
                    .category(dessert)
                    .build();

            dessertMenu.addProductInCourse(ProductInCourse
                    .builder()
                    .product(desserts.get(0))
                    .build());

            dessertMenu.addProductInCourse(ProductInCourse
                    .builder()
                    .product(desserts.get(1))
                    .extraCost(1.5D)
                    .build());

            menu1.addCourse(entreeMenu);
            menu1.addCourse(platMenu);
            menu1.addCourse(dessertMenu);

            this.menuRepository.save(menu1);
        }
    }
}
