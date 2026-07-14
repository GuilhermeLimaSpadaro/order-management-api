package com.gspadaro.ordermanagerapi.service;

import com.gspadaro.ordermanagerapi.model.Category;
import com.gspadaro.ordermanagerapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao existe!"));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
