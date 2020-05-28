package org.clickandcollect.business.impl;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.business.contract.RestaurantService;
import org.clickandcollect.business.exceptions.ResourceDuplicationException;
import org.clickandcollect.business.exceptions.UnknownResourceException;
import org.clickandcollect.consumer.repositories.CategoryRepository;
import org.clickandcollect.consumer.repositories.ProductRepository;
import org.clickandcollect.consumer.repositories.RestaurantRepository;
import org.clickandcollect.model.entities.Category;
import org.clickandcollect.model.entities.Product;
import org.clickandcollect.model.entities.Restaurant;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public RestaurantServiceImpl(RestaurantRepository restaurantRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.restaurantRepository = restaurantRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product addProduct(Long id, Product product) {
        log.info("Retrieving restaurant id {}", id);
        if(this.restaurantRepository.findById(id).isPresent()){
            log.info("Restaurant id {} found", id);
            product.setRestaurant(Restaurant.builder().id(id).build());
            log.info("Retrieving category {}", product.getCategory().getName());
            Optional<Category> category = this.categoryRepository.findCategoryByName(product.getCategory().getName());
            if (category.isPresent()){
                log.info("Category found with id {}", category.get().getId());
                product.setCategory(category.get());
                try {
                    product = this.productRepository.save(product);
                    log.info("Product id {} added to database", product.getId());
                } catch (DataIntegrityViolationException e) {
                    throw new ResourceDuplicationException("Product name '" + product.getName() + "' already exists");
                }
            } else {
                log.warn("Category '{}' does not exists", product.getCategory().getName());
                throw new UnknownResourceException("Unknown category " + product.getCategory().getName());
            }
        } else {
            log.warn("Restaurant id {} does not exists", id);
            throw new UnknownResourceException("Unknown restaurant " + id);
        }
        return product;
    }
}
