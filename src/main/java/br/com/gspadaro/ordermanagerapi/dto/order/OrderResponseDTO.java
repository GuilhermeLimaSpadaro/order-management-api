package br.com.gspadaro.ordermanagerapi.dto.order;

import br.com.gspadaro.ordermanagerapi.model.enums.OrderStatus;

import java.time.Instant;
import java.util.Set;

public record OrderResponseDTO(Long id, Instant moment, OrderStatus orderStatus, Long userId,
                               Set<OrderItemResponseDTO> items) {
}
