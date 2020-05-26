package org.clickandcollect.webservice.controllers;


import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.consumer.CategoryRepository;
import org.clickandcollect.model.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ProductsApi.RESOURCE_URL)
@Slf4j
public class ProductsApi {

    public static final String RESOURCE_URL = "/products";

    private final CategoryRepository categoryRepository;

    public ProductsApi(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getProductCategories(){
        return ResponseEntity.ok(categoryRepository.findAll());
    }
}
