package com.gspadaro.ordermanagerapi.controller;

import com.gspadaro.ordermanagerapi.domain.Order;
import com.gspadaro.ordermanagerapi.dto.OrderRequestDTO;
import com.gspadaro.ordermanagerapi.dto.OrderResponseDTO;
import com.gspadaro.ordermanagerapi.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> create(@RequestBody OrderRequestDTO order) {
        OrderResponseDTO createdOrder = service.create(order);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(createdOrder.id()).toUri();
        return ResponseEntity.created(uri).body(createdOrder);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> update(@PathVariable Long id, @RequestBody OrderRequestDTO order) {
        return ResponseEntity.ok().body(service.update(id, order));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<List<OrderResponseDTO>> findByClient(@PathVariable Long id){
        List<OrderResponseDTO> orders = service.findByCustomerId(id);
        return ResponseEntity.ok().body(orders);
    }
}
