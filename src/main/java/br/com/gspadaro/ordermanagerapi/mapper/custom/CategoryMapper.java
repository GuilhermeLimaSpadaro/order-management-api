package br.com.gspadaro.ordermanagerapi.mapper.custom;

import br.com.gspadaro.ordermanagerapi.dto.product.ProductResponseDTO;
import br.com.gspadaro.ordermanagerapi.model.Category;
import br.com.gspadaro.ordermanagerapi.dto.category.CategoryRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.category.CategoryResponseDTO;
import br.com.gspadaro.ordermanagerapi.mapper.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends ObjectMapper<Category, CategoryRequestDTO, CategoryResponseDTO> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    @Override
    Category toEntity(CategoryRequestDTO request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "products", ignore = true)
    Category toUpdateCategory(CategoryRequestDTO request, @MappingTarget Category category);

    @Override
    CategoryResponseDTO toResponseDTO(Category category);

    @Override
    List<CategoryResponseDTO> toListResponse(List<Category> entityList);
}
