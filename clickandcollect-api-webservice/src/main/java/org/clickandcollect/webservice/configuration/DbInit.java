package org.clickandcollect.webservice.configuration;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.consumer.CategoryRepository;
import org.clickandcollect.model.entities.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class DbInit implements CommandLineRunner {
    // TODO this class is provided for database test purpose
    private final CategoryRepository categoryRepository;

    public DbInit(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
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

        this.categoryRepository.findAll().forEach(el -> log.info(el.toString()));
    }
}
