package com.portfolio.ordermanagerapi.repositories;

import com.portfolio.ordermanagerapi.model.Category;
import com.portfolio.ordermanagerapi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
