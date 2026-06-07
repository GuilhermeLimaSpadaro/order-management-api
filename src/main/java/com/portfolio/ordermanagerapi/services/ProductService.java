package com.portfolio.ordermanagerapi.services;

import com.portfolio.ordermanagerapi.exceptions.DatabaseException;
import com.portfolio.ordermanagerapi.exceptions.ResourceNotFoundException;
import com.portfolio.ordermanagerapi.model.Product;
import com.portfolio.ordermanagerapi.model.User;
import com.portfolio.ordermanagerapi.repositories.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product insert(Product Product) {
        return productRepository.save(Product);
    }

    public void delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        try {
            productRepository.delete(product);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Product update(Long id, Product product) {
        try {
            Product entity = productRepository.getReferenceById(id);
            updateEntity(entity, product);
            return productRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateEntity(Product entity, Product product) {
        entity.setName(product.getName());
        entity.setDescription(product.getDescription());
        entity.setPrice(product.getPrice());
    }

    public Product findProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
