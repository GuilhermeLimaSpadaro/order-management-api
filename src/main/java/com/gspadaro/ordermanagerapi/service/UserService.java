package com.gspadaro.ordermanagerapi.service;

import com.gspadaro.ordermanagerapi.domain.User;
import com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import com.gspadaro.ordermanagerapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user) {
        return repository.save(user);
    }

    public void delete(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        repository.delete(user);
    }

    public User update(Long id, User user) {
        User existingUser = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        User updatedUser = repository.save(existingUser);
        return updatedUser;
    }

    public User findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    public List<User> findAll() {
        return repository.findAll();
    }
}