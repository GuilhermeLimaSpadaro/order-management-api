package com.portfolio.ordermanagerapi.repositories;

import com.portfolio.ordermanagerapi.model.Order;
import com.portfolio.ordermanagerapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
