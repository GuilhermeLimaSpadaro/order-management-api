package br.com.gspadaro.ordermanagerapi.controller;

import br.com.gspadaro.ordermanagerapi.dto.order.OrderRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.order.OrderResponseDTO;
import br.com.gspadaro.ordermanagerapi.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@RequestBody OrderRequestDTO order) {
        OrderResponseDTO createdOrder = orderService.create(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdOrder.id()).toUri();
        return ResponseEntity.created(uri).body(createdOrder);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.findById(id));
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<List<OrderResponseDTO>> findByUserId(@PathVariable Long id) {
        List<OrderResponseDTO> orders = orderService.findByUserId(id);
        return ResponseEntity.ok().body(orders);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> update(@PathVariable Long id, @RequestBody OrderRequestDTO order) {
        return ResponseEntity.ok().body(orderService.update(id, order));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
