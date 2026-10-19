package br.com.gspadaro.ordermanagerapi.service;

import br.com.gspadaro.ordermanagerapi.dto.category.CategoryRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.category.CategoryResponseDTO;
import br.com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import br.com.gspadaro.ordermanagerapi.mapper.custom.CategoryMapper;
import br.com.gspadaro.ordermanagerapi.repository.CategoryRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Transactional
    public CategoryResponseDTO create(CategoryRequestDTO request) {
        var category = categoryMapper.toEntity(request);
        var categorySaved = categoryRepository.save(category);
        return categoryMapper.toResponseDTO(categorySaved);
    }

    @Transactional(readOnly = true)
    public CategoryResponseDTO findById(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        return categoryMapper.toResponseDTO(category);
    }

    @Transactional
    public CategoryResponseDTO update(Long id, CategoryRequestDTO request) {
        var category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        var categoryUpdated = categoryMapper.toUpdateCategory(request, category);
        var categorySaved = categoryRepository.save(categoryUpdated);
        return categoryMapper.toResponseDTO(categorySaved);
    }

    @Transactional
    public void delete(Long id) {
        var category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        categoryRepository.delete(category);
    }
}
