package com.portfolio.ordermanagerapi.services;

import com.portfolio.ordermanagerapi.model.Product;
import com.portfolio.ordermanagerapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao existe!"));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
