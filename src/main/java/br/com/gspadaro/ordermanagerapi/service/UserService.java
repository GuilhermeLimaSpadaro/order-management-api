package br.com.gspadaro.ordermanagerapi.service;

import br.com.gspadaro.ordermanagerapi.dto.user.UserRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.user.UserResponseDTO;
import br.com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import br.com.gspadaro.ordermanagerapi.mapper.custom.UserMapper;
import br.com.gspadaro.ordermanagerapi.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDTO create(UserRequestDTO userRequest) {
        var user = userMapper.toEntity(userRequest);
        var userSaved = userRepository.save(user);
        return userMapper.toResponseDTO(userSaved);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        return userMapper.toResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO update(Long id, UserRequestDTO userRequest) {
        var user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        var userUpdated = userMapper.toUpdateUser(userRequest, user);
        var userSaved = userRepository.save(userUpdated);
        return userMapper.toResponseDTO(userSaved);

    }

    @Transactional
    public void delete(Long id) {
        var user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found. ID:" + id));
        userRepository.delete(user);
    }
}