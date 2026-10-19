package br.com.gspadaro.ordermanagerapi.dto.product;

public record ProductRequestDTO(String name, String description, Double price, String imgUrl) {
}
