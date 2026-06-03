package com.portfolio.ordermanagerapi.services;

import com.portfolio.ordermanagerapi.model.Category;
import com.portfolio.ordermanagerapi.repositories.CategoryRepository;
import com.portfolio.ordermanagerapi.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category insert(Category Category) {
        return categoryRepository.save(Category);
    }

    public void delete(Long id) {
        Category Category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        categoryRepository.delete(Category);
    }

    public Category update(Long id, Category category) {
        Category entity = categoryRepository.getReferenceById(id);
        updateEntity(entity, category);
        return categoryRepository.save(entity);
    }

    public void updateEntity(Category entity, Category category){
        entity.setName(category.getName());
    }

    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
