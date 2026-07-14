package com.gspadaro.ordermanagerapi.repository;

import com.gspadaro.ordermanagerapi.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
