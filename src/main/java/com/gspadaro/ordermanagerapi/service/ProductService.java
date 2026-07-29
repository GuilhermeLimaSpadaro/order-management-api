package com.gspadaro.ordermanagerapi.service;

import com.gspadaro.ordermanagerapi.domain.Product;
import com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import com.gspadaro.ordermanagerapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product) {
        return repository.save(product);
    }

    public void delete(Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        repository.delete(product);
    }

    public Product update(Long id, Product product) {
        Product existingProduct = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImgUrl(product.getImgUrl());
        return repository.save(existingProduct);
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
    }

    public List<Product> findAll() {
        return repository.findAll();
    }
}
