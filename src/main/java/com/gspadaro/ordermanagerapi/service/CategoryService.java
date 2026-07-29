package com.gspadaro.ordermanagerapi.service;

import com.gspadaro.ordermanagerapi.domain.Category;
import com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import com.gspadaro.ordermanagerapi.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category create(Category category) {
        return repository.save(category);
    }

    public void delete(Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        repository.delete(category);
    }

    public Category update(Long id, Category category) {
        Category existingCategory = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        existingCategory.setName(category.getName());
        return repository.save(existingCategory);
    }

    public Category findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
    }

    public List<Category> findAll() {
        return repository.findAll();
    }
}
