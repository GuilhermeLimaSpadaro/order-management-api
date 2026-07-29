package com.gspadaro.ordermanagerapi.service;

import com.gspadaro.ordermanagerapi.domain.Order;
import com.gspadaro.ordermanagerapi.domain.User;
import com.gspadaro.ordermanagerapi.dto.OrderRequestDTO;
import com.gspadaro.ordermanagerapi.dto.OrderResponseDTO;
import com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import com.gspadaro.ordermanagerapi.mapper.OrderMapper;
import com.gspadaro.ordermanagerapi.repository.OrderRepository;
import com.gspadaro.ordermanagerapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.orderMapper = orderMapper;
    }

    public OrderResponseDTO create(OrderRequestDTO orderRequest) {
        User user = userRepository.findById(orderRequest.customerId()).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        Order order = orderMapper.toEntity(orderRequest, user);
        Order orderCreate = orderRepository.save(order);
        return orderMapper.toResponseDTO(orderCreate);
    }

    public void delete(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        orderRepository.delete(order);
    }

    public OrderResponseDTO update(Long id, OrderRequestDTO orderRequest) {
        Order existingOrder = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        existingOrder.setMoment(orderRequest.moment());
        existingOrder.setOrderStatus(orderRequest.orderStatus());
        User existingUser = userRepository.findById(orderRequest.customerId()).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        existingOrder.setCustomer(existingUser);
        Order orderUpdate = orderRepository.save(existingOrder);
        return orderMapper.toResponseDTO(orderUpdate);
    }

    public OrderResponseDTO findById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        return orderMapper.toResponseDTO(order);
    }

    public List<OrderResponseDTO> findAll() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toResponseDTOList(orders);
    }

    public List<OrderResponseDTO> findByCustomerId(Long id) {
        List<Order> orders = orderRepository.findByCustomerId(id);
        return orderMapper.toResponseDTOList(orders);
    }
}
