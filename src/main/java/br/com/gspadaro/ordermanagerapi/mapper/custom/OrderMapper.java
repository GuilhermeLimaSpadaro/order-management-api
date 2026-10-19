package br.com.gspadaro.ordermanagerapi.mapper.custom;

import br.com.gspadaro.ordermanagerapi.model.Order;
import br.com.gspadaro.ordermanagerapi.dto.order.OrderRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.order.OrderResponseDTO;
import br.com.gspadaro.ordermanagerapi.dto.user.UserResponseDTO;
import br.com.gspadaro.ordermanagerapi.mapper.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper extends ObjectMapper<Order, OrderRequestDTO, OrderResponseDTO> {

    @Mapping(target = "id", ignore = true)
    @Override
    Order toEntity(OrderRequestDTO request);

    @Mapping(target = "id", ignore = true)
    Order toUpdateOrder(OrderRequestDTO request, @MappingTarget Order order);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "moment", source = "request.moment")
    @Mapping(target = "orderStatus", source = "request.orderStatus")
    @Mapping(target = "items", source = "request.items")
    @Mapping(target = "user.id", source = "response.id")
    Order toEntityWithUser(OrderRequestDTO request, UserResponseDTO response);

    @Mapping(target = "userId", ignore = true)
    @Override
    OrderResponseDTO toResponseDTO(Order order);

    @Override
    List<OrderResponseDTO> toListResponse(List<Order> responseList);
}
