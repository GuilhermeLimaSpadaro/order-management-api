package com.gspadaro.ordermanagerapi.controller;

import com.gspadaro.ordermanagerapi.model.Product;
import com.gspadaro.ordermanagerapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> listAll() {
        List<Product> ProductList = productService.findAll();

        return ResponseEntity.ok().body(ProductList);
    }
}
