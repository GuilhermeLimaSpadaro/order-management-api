package br.com.gspadaro.ordermanagerapi.service;

import br.com.gspadaro.ordermanagerapi.dto.category.CategoryRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.category.CategoryResponseDTO;
import br.com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import br.com.gspadaro.ordermanagerapi.mapper.custom.CategoryMapper;
import br.com.gspadaro.ordermanagerapi.repository.CategoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final Logger logger = LoggerFactory.getLogger(CategoryService.class);

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Transactional
    public CategoryResponseDTO create(CategoryRequestDTO request) {
        logger.info("Create Category.");
        var category = categoryMapper.toEntity(request);
        var savedCategory = categoryRepository.save(category);
        logger.info("Category successfully created. ID: {}", savedCategory.getId());
        return categoryMapper.toResponseDTO(savedCategory);
    }

    @Transactional(readOnly = true)
    public CategoryResponseDTO findById(Long categoryId) {
        logger.info("Finding Category.");
        var category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            logger.warn("Category not found on findById. ID: {}", categoryId);
            return new ResourceNotFoundException("Category not found with ID: " + categoryId);
        });
        logger.info("Category successfully found. ID: {}", categoryId);
        return categoryMapper.toResponseDTO(category);
    }

    @Transactional
    public CategoryResponseDTO update(Long categoryId, CategoryRequestDTO request) {
        logger.info("Update Category.");
        var category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            logger.warn("Category not found on update. ID: {}", categoryId);
            return new ResourceNotFoundException("Category not found with ID: " + categoryId);
        });
        var categoryUpdated = categoryMapper.toUpdateCategory(request, category);
        var categorySaved = categoryRepository.save(categoryUpdated);
        logger.info("Category successfully updated. ID: {}", categoryId);
        return categoryMapper.toResponseDTO(categorySaved);
    }

    @Transactional
    public void delete(Long categoryId) {
        logger.info("Category Delete.");
        var category = categoryRepository.findById(categoryId).orElseThrow(() -> {
            logger.warn("Category not found on delete. ID: {}", categoryId);
            return new ResourceNotFoundException("Category not found with ID: " + categoryId);
        });
        categoryRepository.delete(category);
        logger.info("User successfully deleted. ID: {}", categoryId);
    }
}
