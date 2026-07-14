package com.gspadaro.ordermanagerapi.controller;

import com.gspadaro.ordermanagerapi.model.Order;
import com.gspadaro.ordermanagerapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService OrderService;

        @GetMapping("/{id}")
        public ResponseEntity<Order> findOrderById(@PathVariable Long id) {
            return ResponseEntity.ok().body(OrderService.findById(id));
        }

        @GetMapping
        public ResponseEntity<List<Order>> listAll() {
            List<Order> OrderList = OrderService.findAll();
            return ResponseEntity.ok().body(OrderList);
        }
    }
