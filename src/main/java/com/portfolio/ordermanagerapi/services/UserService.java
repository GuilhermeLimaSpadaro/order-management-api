package com.portfolio.ordermanagerapi.services;

import com.portfolio.ordermanagerapi.model.User;
import com.portfolio.ordermanagerapi.repositories.UserRepository;
import com.portfolio.ordermanagerapi.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User insert(User User) {
        return userRepository.save(User);
    }

    public void delete(Long id) {
        User User = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
        userRepository.delete(User);
    }

    public User update(Long id, User user) {
        User entity = userRepository.getReferenceById(id);
        updateEntity(entity, user);
        return userRepository.save(entity);
    }

    public void updateEntity(User entity, User user) {
        entity.setName(user.getName());
        entity.setEmail(user.getEmail());
        entity.setPhone(user.getPhone());
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
