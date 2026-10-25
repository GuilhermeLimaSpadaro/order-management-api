package br.com.gspadaro.ordermanagerapi.mapper.custom;

import br.com.gspadaro.ordermanagerapi.dto.order.OrderItemRequestDTO;
import br.com.gspadaro.ordermanagerapi.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "product", ignore = true)
    OrderItem toEntity(OrderItemRequestDTO request);

    List<OrderItem> toListEntity(List<OrderItemRequestDTO> requestList);
}
