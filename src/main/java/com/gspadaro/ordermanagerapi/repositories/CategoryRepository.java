package com.gspadaro.ordermanagerapi.repositories;

import com.gspadaro.ordermanagerapi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
