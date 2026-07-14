package com.gspadaro.ordermanagerapi.controller;

import com.gspadaro.ordermanagerapi.model.User;
import com.gspadaro.ordermanagerapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

        @GetMapping("/{id}")
        public ResponseEntity<User> findUserById(@PathVariable Long id) {
            return ResponseEntity.ok().body(userService.findById(id));
        }

        @GetMapping
        public ResponseEntity<List<User>> listAll() {
            List<User> userList = userService.findAll();
            return ResponseEntity.ok().body(userList);
        }
    }
