package com.portfolio.ordermanagerapi.services;

import com.portfolio.ordermanagerapi.model.Product;
import com.portfolio.ordermanagerapi.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product Product) {
        return productRepository.save(Product);
    }

    public void delete(Long id) {
        Product Product = productRepository.findById(id).orElseThrow(() -> new NullPointerException("Pedido nao encontrado!"));
        productRepository.delete(Product);
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao existe!"));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
