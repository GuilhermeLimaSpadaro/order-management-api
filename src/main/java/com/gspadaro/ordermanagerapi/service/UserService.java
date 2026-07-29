package com.gspadaro.ordermanagerapi.service;

import com.gspadaro.ordermanagerapi.dto.UserRequestDTO;
import com.gspadaro.ordermanagerapi.mapper.UserMapper;
import com.gspadaro.ordermanagerapi.domain.User;
import com.gspadaro.ordermanagerapi.dto.UserResponseDTO;
import com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import com.gspadaro.ordermanagerapi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UserResponseDTO create(UserRequestDTO userRequest) {
        User user = mapper.toEntity(userRequest);
        User userCreate = repository.save(user);
        return mapper.toResponseDTO(userCreate);
    }

    public void delete(Long id) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        repository.delete(user);
    }

    public UserResponseDTO update(Long id, UserRequestDTO userRequest) {
        User user = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPhone(userRequest.phone());
        User updatedUser = repository.save(user);
        return mapper.toResponseDTO(updatedUser);

    }

    public UserResponseDTO findById(Long id) {
        User findUser = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        return mapper.toResponseDTO(findUser);
    }

    public List<UserResponseDTO> findAll() {
        List<User> users = repository.findAll();
        return mapper.toResponseDTOList(users);
    }
}