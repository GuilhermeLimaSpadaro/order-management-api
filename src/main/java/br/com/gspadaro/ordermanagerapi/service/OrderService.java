package br.com.gspadaro.ordermanagerapi.service;

import br.com.gspadaro.ordermanagerapi.dto.order.OrderRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.order.OrderResponseDTO;
import br.com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import br.com.gspadaro.ordermanagerapi.mapper.custom.OrderMapper;
import br.com.gspadaro.ordermanagerapi.repository.OrderRepository;
import br.com.gspadaro.ordermanagerapi.repository.ProductRepository;
import br.com.gspadaro.ordermanagerapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;
    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public OrderService(OrderRepository orderRepository, UserRepository userRepository, ProductRepository productRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public OrderResponseDTO create(OrderRequestDTO request) {
        logger.info("Create order");
        var user = userRepository.findById(request.userId()).orElseThrow(() ->
                new ResourceNotFoundException("User not found with ID: " + request.userId()));
        var products = request.items().stream().map(orderItem -> productRepository.findById(orderItem.productId()).orElseThrow(() ->
                                new ResourceNotFoundException("Product not found on order create. ID: " + orderItem.productId()))).toList();
        var order = orderMapper.toEntity(request, user, products);
        orderMapper.populateItems(order, request, products);
        var orderCreated = orderRepository.save(order);
        logger.info("Order successfully created. ID: {}", orderCreated.getId());
        return orderMapper.toResponseDTO(orderCreated);
    }

    @Transactional(readOnly = true)
    public OrderResponseDTO findById(Long orderId) {
        logger.info("Finding Order.");
        var order = orderRepository.findById(orderId).orElseThrow(() -> {
            logger.warn("Order not found on findById. ID: {}", orderId);
            return new ResourceNotFoundException("Order not found with ID: " + orderId);
        });
        logger.info("Order successfully found. ID: {}", orderId);
        return orderMapper.toResponseDTO(order);
    }

    @Transactional(readOnly = true)
    public List<OrderResponseDTO> findByUserId(Long userId) {
        logger.info("Finding orders by user id.");
        var orders = orderRepository.findByUserId(userId);
        logger.info("Orders successfully found. User ID: {}", userId);
        return orderMapper.toListResponse(orders);
    }

    @Transactional
    public OrderResponseDTO update(Long orderId, OrderRequestDTO request) {
        logger.info("Update Order.");
        var order = orderRepository.findById(orderId).orElseThrow(() -> {
            logger.warn("User not found on update. ID: {}", orderId);
            return new ResourceNotFoundException("Order not found with ID: " + orderId);
        });
        var updated = orderMapper.toUpdateOrder(request, order);
        var orderUpdated = orderRepository.save(updated);
        logger.info("Order successfully updated. ID: {}", orderId);
        return orderMapper.toResponseDTO(orderUpdated);
    }

    @Transactional
    public void delete(Long orderId) {
        logger.info("Order Delete.");
        var order = orderRepository.findById(orderId).orElseThrow(() -> {
            logger.warn("Order not found on delete. ID: {}", orderId);
            return new ResourceNotFoundException("Order not found with ID: " + orderId);
        });
        orderRepository.delete(order);
        logger.info("User successfully deleted. ID: {}", orderId);
    }
}
