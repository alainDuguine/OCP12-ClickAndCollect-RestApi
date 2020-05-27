package org.clickandcollect.webservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.consumer.CategoryRepository;
import org.clickandcollect.webservice.dto.CategoryDto;
import org.clickandcollect.webservice.mappers.CategoryMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ProductsApi.RESOURCE_URL)
@Slf4j
public class ProductsApi {

    public static final String RESOURCE_URL = "/products";

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public ProductsApi(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

//    @GetMapping("/categories")
//    public ResponseEntity<CategoriesDto> getProductCategories(){
//        return ResponseEntity.ok(new CategoriesDto(categoryMapper.categoryListToDtoList(categoryRepository.findAll())));
//    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getProductCategories(){
        return ResponseEntity.ok(categoryMapper.categoryListToDtoList(categoryRepository.findAll()));
    }

    @PostMapping()
}
