package com.gspadaro.ordermanagerapi.service;

import com.gspadaro.ordermanagerapi.domain.Order;
import com.gspadaro.ordermanagerapi.domain.Order;
import com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import com.gspadaro.ordermanagerapi.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order create(Order order) {
        return repository.save(order);
    }

    public void delete(Long id) {
        Order Order = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        repository.delete(Order);
    }

    public Order update(Long id, Order order) {
        Order existingOrder = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        existingOrder.setMoment(order.getMoment());
        existingOrder.setOrderStatus(order.getOrderStatus());
        existingOrder.setClient(order.getClient());
        return repository.save(existingOrder);
    }

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public List<Order> findAll() {
        return repository.findAll();
    }
}
