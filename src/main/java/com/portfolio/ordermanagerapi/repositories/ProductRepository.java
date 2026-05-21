package com.portfolio.ordermanagerapi.repositories;

import com.portfolio.ordermanagerapi.model.Order;
import com.portfolio.ordermanagerapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
