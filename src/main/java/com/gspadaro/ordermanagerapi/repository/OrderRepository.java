package com.gspadaro.ordermanagerapi.repository;

import com.gspadaro.ordermanagerapi.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
