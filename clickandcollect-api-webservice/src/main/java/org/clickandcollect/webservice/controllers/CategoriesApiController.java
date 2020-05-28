package org.clickandcollect.webservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.consumer.repositories.CategoryRepository;
import org.clickandcollect.webservice.dtos.CategoryDto;
import org.clickandcollect.webservice.mappers.CategoryMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CategoriesApiController.RESOURCE_URL)
@Slf4j
public class CategoriesApiController {

    public static final String RESOURCE_URL = "/categories";

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoriesApiController(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

//    @GetMapping("/categories")
//    public ResponseEntity<CategoriesDto> getProductCategories(){
//        return ResponseEntity.ok(new CategoriesDto(categoryMapper.categoryListToDtoList(categoryRepository.findAll())));
//    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDto>> getCategories(){
        log.info("Request for categories, returning {} results", this.categoryRepository.count());
        return ResponseEntity.ok(categoryMapper.categoryListToDtoList(categoryRepository.findAll()));
    }

}
