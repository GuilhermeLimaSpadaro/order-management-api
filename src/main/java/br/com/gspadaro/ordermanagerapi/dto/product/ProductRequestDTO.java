package br.com.gspadaro.ordermanagerapi.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Positive;

public record ProductRequestDTO(@NotBlank String name, @NotBlank String description, @Positive @NotNull Double price, String imgUrl) {
}
