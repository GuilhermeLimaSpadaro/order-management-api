package com.portfolio.ordermanagerapi.services;

import com.portfolio.ordermanagerapi.exceptions.DatabaseException;
import com.portfolio.ordermanagerapi.exceptions.ResourceNotFoundException;
import com.portfolio.ordermanagerapi.model.User;
import com.portfolio.ordermanagerapi.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
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
        try {
            userRepository.delete(User);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long id, User user) {
        try {
            User entity = userRepository.getReferenceById(id);
            updateEntity(entity, user);
            return userRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
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
