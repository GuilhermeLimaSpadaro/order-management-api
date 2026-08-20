package br.com.gspadaro.ordermanagerapi.service;

import br.com.gspadaro.ordermanagerapi.dto.user.UserRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.user.UserResponseDTO;
import br.com.gspadaro.ordermanagerapi.exception.ResourceNotFoundException;
import br.com.gspadaro.ordermanagerapi.mapper.custom.UserMapper;
import br.com.gspadaro.ordermanagerapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserResponseDTO create(UserRequestDTO userRequest) {
        logger.info("Create User.");
        var user = userMapper.toEntity(userRequest);
        var savedUser = userRepository.save(user);
        logger.info("User successfully created. ID: {}", savedUser.getId());
        return userMapper.toResponseDTO(savedUser);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO findById(Long userId) {
        logger.info("Finding User.");

        var user = userRepository.findById(userId).orElseThrow(() -> {
            logger.warn("User not found on findById. ID: {}", userId);
            return new ResourceNotFoundException("User not found with ID: " + userId);
        });

        logger.info("User successfully found. ID: {}", userId);
        return userMapper.toResponseDTO(user);
    }

    @Transactional
    public UserResponseDTO update(Long userId, UserRequestDTO userRequest) {
        logger.info("Update User.");

        var user = userRepository.findById(userId).orElseThrow(() -> {
            logger.warn("User not found on update. ID: {}", userId);
            return new ResourceNotFoundException("User not found with ID: " + userId);
        });

        var userUpdated = userMapper.toUpdateUser(userRequest, user);
        var userSaved = userRepository.save(userUpdated);
        logger.info("User successfully updated. ID: {}", userId);
        return userMapper.toResponseDTO(userSaved);

    }

    @Transactional
    public void delete(Long userId) {
        logger.info("User Delete.");
        var user = userRepository.findById(userId).orElseThrow(() -> {
            logger.warn("User not found on delete. ID: {}", userId);
            return new ResourceNotFoundException("User not found with ID: " + userId);
        });
        userRepository.delete(user);
        logger.info("User successfully deleted. ID: {}", userId);
    }
}