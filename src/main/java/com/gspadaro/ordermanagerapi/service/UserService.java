package com.gspadaro.ordermanagerapi.service;

import com.gspadaro.ordermanagerapi.model.User;
import com.gspadaro.ordermanagerapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Object not found!"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
