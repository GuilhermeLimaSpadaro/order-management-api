package com.gspadaro.ordermanagerapi.service;

import com.gspadaro.ordermanagerapi.domain.Category;
import com.gspadaro.ordermanagerapi.domain.Category;
import com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import com.gspadaro.ordermanagerapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category create(Category category) {
        return repository.save(category);
    }

    public void delete(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        repository.delete(category);
    }

    public Category update(Long id, Category category) {
        Category existingCategory = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
        existingCategory.setName(category.getName());
        return repository.save(existingCategory);
    }

    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Category not found with id: " + id));
    }

    public List<Category> findAll() {
        return repository.findAll();
    }
}
