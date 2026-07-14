package com.gspadaro.ordermanagerapi.service;

import com.gspadaro.ordermanagerapi.model.Product;
import com.gspadaro.ordermanagerapi.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao existe!"));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
