package br.com.gspadaro.ordermanagerapi.dto.order;

public record OrderItemRequestDTO(Long productId, Integer quantity, Double price) {
}
