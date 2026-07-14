package com.gspadaro.ordermanagerapi.service;

import com.gspadaro.ordermanagerapi.domain.Product;
import com.gspadaro.ordermanagerapi.domain.Product;
import com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import com.gspadaro.ordermanagerapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product Product) {
        return repository.save(Product);
    }

    public void delete(Long id) {
        Product Product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        repository.delete(Product);
    }

    public Product update(Long id, Product product) {
        Product existingProduct = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setImgUrl(product.getImgUrl());
        return repository.save(existingProduct);
    }

    public Product findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public List<Product> findAll() {
        return repository.findAll();
    }
}
