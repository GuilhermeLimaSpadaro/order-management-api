package com.portfolio.ordermanagerapi.services;

import com.portfolio.ordermanagerapi.model.Category;
import com.portfolio.ordermanagerapi.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category Category) {
        return categoryRepository.save(Category);
    }

    public void delete(Long id) {
        Category Category = categoryRepository.findById(id).orElseThrow(() -> new NullPointerException("Pedido nao encontrado!"));
        categoryRepository.delete(Category);
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao existe!"));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
