package br.com.gspadaro.ordermanagerapi.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(@NotBlank String name, @Email @NotBlank String email, @NotBlank @Size(min = 9, max = 9) String phone) {
}
