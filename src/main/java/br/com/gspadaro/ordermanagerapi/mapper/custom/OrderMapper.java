package br.com.gspadaro.ordermanagerapi.mapper.custom;

import br.com.gspadaro.ordermanagerapi.dto.order.OrderItemRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.order.OrderRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.order.OrderResponseDTO;
import br.com.gspadaro.ordermanagerapi.mapper.ObjectMapper;
import br.com.gspadaro.ordermanagerapi.model.Order;
import br.com.gspadaro.ordermanagerapi.model.OrderItem;
import br.com.gspadaro.ordermanagerapi.model.Product;
import br.com.gspadaro.ordermanagerapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper extends ObjectMapper<Order, OrderRequestDTO, OrderResponseDTO> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "items", ignore = true)
    Order toEntity(OrderRequestDTO order, User user, List<Product> products);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "items", ignore = true)
    Order toUpdateOrder(OrderRequestDTO request, @MappingTarget Order order);

    @Mapping(target = "userId", source = "user.id")
    OrderResponseDTO toResponseDTO(Order order);

    @Override
    List<OrderResponseDTO> toListResponse(List<Order> responseList);

    default void populateItems(Order order, OrderRequestDTO request, List<Product> products) {
        for (OrderItemRequestDTO itemDTO : request.items()) {
            Product product = products.stream()
                    .filter(p -> p.getId().equals(itemDTO.productId()))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + itemDTO.productId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDTO.quantity());
            orderItem.setPrice(itemDTO.price());

            order.getItems().add(orderItem);
        }
    }
}
