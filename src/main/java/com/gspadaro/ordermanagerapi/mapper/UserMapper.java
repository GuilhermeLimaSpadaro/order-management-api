package com.gspadaro.ordermanagerapi.mapper;

import com.gspadaro.ordermanagerapi.domain.User;
import com.gspadaro.ordermanagerapi.dto.UserRequestDTO;
import com.gspadaro.ordermanagerapi.dto.UserResponseDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserResponseDTO toResponseDTO(User user) {
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }

    public User toEntity(UserRequestDTO userRequest) {
        User user = new User();
        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setPhone(userRequest.phone());
        return user;
    }

    public List<UserResponseDTO> toResponseDTOList(List<User> users) {
        return users.stream().map(this::toResponseDTO).toList();
    }
}
