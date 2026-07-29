package com.gspadaro.ordermanagerapi.mapper;

import com.gspadaro.ordermanagerapi.domain.Order;
import com.gspadaro.ordermanagerapi.domain.User;
import com.gspadaro.ordermanagerapi.dto.OrderRequestDTO;
import com.gspadaro.ordermanagerapi.dto.OrderResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    private final UserMapper userMapper;

    public OrderMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public OrderResponseDTO toResponseDTO(Order order) {
        return new OrderResponseDTO(order.getId(), order.getMoment(), order.getOrderStatus(), order.getCustomer().getId(), order.getItems());
    }

    public Order toEntity(OrderRequestDTO orderRequest, User customer) {
        Order toEntity = new Order();
        toEntity.setMoment(orderRequest.moment());
        toEntity.setOrderStatus(orderRequest.orderStatus());
        toEntity.setCustomer(customer);
        return toEntity;
    }

    public List<OrderResponseDTO> toResponseDTOList(List<Order> orders) {
        return orders.stream().map(this::toResponseDTO).toList();
    }
}
