package com.portfolio.ordermanagerapi.resources;

import com.portfolio.ordermanagerapi.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResource {
    @GetMapping
    public ResponseEntity<User> listAll() {
        User u = new User(1L, "Joao", "1111111111", "12345");
        return ResponseEntity.ok().body(u);
    }
}
