package com.portfolio.ordermanagerapi.services;

import com.portfolio.ordermanagerapi.model.Category;
import com.portfolio.ordermanagerapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao existe!"));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
