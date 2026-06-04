package com.portfolio.ordermanagerapi.services;

import com.portfolio.ordermanagerapi.model.Order;
import com.portfolio.ordermanagerapi.repositories.OrderRepository;
import com.portfolio.ordermanagerapi.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order insert(Order order) {
        return orderRepository.save(order);
    }

    public void delete(Long id) {

        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        orderRepository.delete(order);
    }

    public Order update(Long id, Order order) {
        try {
            Order entity = orderRepository.getReferenceById(id);
            updateEntity(entity, order);
            return orderRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    public void updateEntity(Order entity, Order obj) {
        entity.setOrderStatus(obj.getOrderStatus());
        entity.setPayment(obj.getPayment());
    }

    public Order findOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}
