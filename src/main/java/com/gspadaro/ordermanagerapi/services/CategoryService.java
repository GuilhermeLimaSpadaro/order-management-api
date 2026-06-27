package com.gspadaro.ordermanagerapi.services;

import com.gspadaro.ordermanagerapi.exceptions.DatabaseException;
import com.gspadaro.ordermanagerapi.exceptions.ResourceNotFoundException;
import com.gspadaro.ordermanagerapi.model.Category;
import com.gspadaro.ordermanagerapi.repositories.CategoryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category insert(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        try {
            categoryRepository.delete(category);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Category update(Long id, Category category) {
        Category entity = categoryRepository.getReferenceById(id);
        updateEntity(entity, category);
        return categoryRepository.save(entity);
    }

    public void updateEntity(Category entity, Category category) {
        entity.setName(category.getName());
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
