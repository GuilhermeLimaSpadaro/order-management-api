package br.com.gspadaro.ordermanagerapi.dto.order;

import br.com.gspadaro.ordermanagerapi.model.enums.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;

public record OrderRequestDTO(@NotNull Instant moment, @NotNull OrderStatus orderStatus, @NotNull Long userId, @NotEmpty List<OrderItemRequestDTO> items) {
}
