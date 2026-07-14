package com.gspadaro.ordermanagerapi.service;

import com.gspadaro.ordermanagerapi.model.Order;
import com.gspadaro.ordermanagerapi.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository OrderRepository;

    public OrderService(OrderRepository orderRepository) {
        OrderRepository = orderRepository;
    }

    public Order findById(Long id) {
        return OrderRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao existe!"));
    }

    public List<Order> findAll() {
        return OrderRepository.findAll();
    }
}
