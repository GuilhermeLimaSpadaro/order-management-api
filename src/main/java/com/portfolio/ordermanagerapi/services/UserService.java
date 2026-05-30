package com.portfolio.ordermanagerapi.services;

import com.portfolio.ordermanagerapi.model.User;
import com.portfolio.ordermanagerapi.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User User) {
        return userRepository.save(User);
    }

    public void delete(Long id) {
        User User = userRepository.findById(id).orElseThrow(() -> new NullPointerException("Pedido nao encontrado!"));
        userRepository.delete(User);
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario nao existe!"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
