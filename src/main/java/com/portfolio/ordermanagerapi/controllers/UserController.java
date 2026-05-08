package com.portfolio.ordermanagerapi.controllers;

import com.portfolio.ordermanagerapi.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity<User> listAll() {
        User u = new User(1L, "Joao", "Joao@gmail.com", "111111111", "12345");
        return ResponseEntity.ok().body(u);
    }
}
