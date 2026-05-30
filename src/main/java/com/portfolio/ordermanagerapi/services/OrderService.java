package com.portfolio.ordermanagerapi.services;

import com.portfolio.ordermanagerapi.model.Order;
import com.portfolio.ordermanagerapi.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void delete(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new NullPointerException("Pedido nao encontrado!"));
        orderRepository.delete(order);
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao existe!"));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
