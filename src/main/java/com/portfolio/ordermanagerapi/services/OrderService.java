package com.portfolio.ordermanagerapi.services;

import com.portfolio.ordermanagerapi.model.Order;
import com.portfolio.ordermanagerapi.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository OrderRepository;

    public Order findOrderById(Long id) {
        return OrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao existe!"));
    }

    public List<Order> findAll() {
        return OrderRepository.findAll();
    }
}
