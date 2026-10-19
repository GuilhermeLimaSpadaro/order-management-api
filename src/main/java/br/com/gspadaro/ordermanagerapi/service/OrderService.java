package br.com.gspadaro.ordermanagerapi.service;

import br.com.gspadaro.ordermanagerapi.dto.order.OrderRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.order.OrderResponseDTO;
import br.com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import br.com.gspadaro.ordermanagerapi.mapper.custom.OrderMapper;
import br.com.gspadaro.ordermanagerapi.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, UserService userService, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderResponseDTO create(OrderRequestDTO request) {
        var user = userService.findById(request.userId());
        var order = orderMapper.toEntityWithUser(request, user);
        var orderCreated = orderRepository.save(order);
        return orderMapper.toResponseDTO(orderCreated);
    }

    @Transactional(readOnly = true)
    public OrderResponseDTO findById(Long id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        return orderMapper.toResponseDTO(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDTO> findByUserId(Long id) {
        var orders = orderRepository.findByUserId(id);
        return orderMapper.toListResponse(orders);
    }

    @Transactional
    public OrderResponseDTO update(Long id, OrderRequestDTO request) {
        var order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        var updated = orderMapper.toUpdateOrder(request, order);
        var orderUpdated = orderRepository.save(updated);
        return orderMapper.toResponseDTO(orderUpdated);
    }

    @Transactional
    public void delete(Long id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        orderRepository.delete(order);
    }
}
