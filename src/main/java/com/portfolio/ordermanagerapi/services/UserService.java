package com.portfolio.ordermanagerapi.services;

import com.portfolio.ordermanagerapi.model.User;
import com.portfolio.ordermanagerapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao existe!"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
