package br.com.gspadaro.ordermanagerapi.mapper.custom;

import br.com.gspadaro.ordermanagerapi.model.Product;
import br.com.gspadaro.ordermanagerapi.dto.product.ProductRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.product.ProductResponseDTO;
import br.com.gspadaro.ordermanagerapi.mapper.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper extends ObjectMapper<Product, ProductRequestDTO, ProductResponseDTO> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Override
    Product toEntity(ProductRequestDTO request);

    @Mapping(target = "name", source = "request.name")
    @Mapping(target = "description", source = "request.description")
    @Mapping(target = "price", source = "request.price")
    @Mapping(target = "imgUrl", source = "request.imgUrl")
    Product toUpdateOrder(ProductRequestDTO request, Product product);

    @Override
    ProductResponseDTO toResponseDTO(Product product);

    @Override
    List<ProductResponseDTO> toListResponse(List<Product> entityList);
}
