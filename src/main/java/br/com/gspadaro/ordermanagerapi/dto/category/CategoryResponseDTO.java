package br.com.gspadaro.ordermanagerapi.dto.category;

import br.com.gspadaro.ordermanagerapi.dto.product.ProductResponseDTO;

import java.util.List;

public record CategoryResponseDTO(Long id, String name, List<ProductResponseDTO> products) {
}
