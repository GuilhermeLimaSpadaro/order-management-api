package com.gspadaro.ordermanagerapi.controller;

import com.gspadaro.ordermanagerapi.model.Category;
import com.gspadaro.ordermanagerapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

        @GetMapping("/{id}")
        public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
            return ResponseEntity.ok().body(categoryService.findById(id));
        }

        @GetMapping
        public ResponseEntity<List<Category>> listAll() {
            List<Category> CategoryList = categoryService.findAll();

            return ResponseEntity.ok().body(CategoryList);
        }
    }
