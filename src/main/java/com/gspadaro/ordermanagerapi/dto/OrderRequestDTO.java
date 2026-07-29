package com.gspadaro.ordermanagerapi.dto;

import com.gspadaro.ordermanagerapi.domain.OrderItem;
import com.gspadaro.ordermanagerapi.domain.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderRequestDTO(LocalDateTime moment, OrderStatus orderStatus, Long customerId, Set<OrderItem> items) {
}
