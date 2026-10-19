package br.com.gspadaro.ordermanagerapi.dto.order;

import br.com.gspadaro.ordermanagerapi.model.OrderItem;
import br.com.gspadaro.ordermanagerapi.model.enums.OrderStatus;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderResponseDTO(Long id, LocalDateTime moment, OrderStatus orderStatus, Long userId, Set<OrderItemResponseDTO> items) {
}
