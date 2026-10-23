package br.com.gspadaro.ordermanagerapi.dto.category;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDTO(@NotBlank String name) {
}
