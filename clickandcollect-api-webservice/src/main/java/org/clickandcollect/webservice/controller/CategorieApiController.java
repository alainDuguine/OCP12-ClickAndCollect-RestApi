package org.clickandcollect.webservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.clickandcollect.consumer.repository.CategoryRepository;
import org.clickandcollect.webservice.dto.CategoryDto;
import org.clickandcollect.webservice.mapper.CategoryMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
@Slf4j
public class CategorieApiController {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategorieApiController(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @GetMapping()
    public ResponseEntity<List<CategoryDto>> getCategories(){
        log.info("Request for categories, returning '{}' results", this.categoryRepository.count());
        return ResponseEntity.ok(categoryMapper.categoryListToDtoList(categoryRepository.findAll()));
    }

}
