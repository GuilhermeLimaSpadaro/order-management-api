package com.portfolio.ordermanagerapi.controllers;

import com.portfolio.ordermanagerapi.model.Order;
import com.portfolio.ordermanagerapi.services.OrderService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order){
        return ResponseEntity.ok().body(orderService.save(order));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findOrderById(@PathVariable Long id) {
        return ResponseEntity.ok().body(orderService.findOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<Order>> listAll() {
        List<Order> OrderList = orderService.findAll();
        return ResponseEntity.ok().body(OrderList);
    }
}
