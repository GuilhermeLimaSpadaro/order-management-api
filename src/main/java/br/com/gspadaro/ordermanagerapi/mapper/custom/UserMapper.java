package br.com.gspadaro.ordermanagerapi.mapper.custom;

import br.com.gspadaro.ordermanagerapi.model.User;
import br.com.gspadaro.ordermanagerapi.dto.user.UserRequestDTO;
import br.com.gspadaro.ordermanagerapi.dto.user.UserResponseDTO;
import br.com.gspadaro.ordermanagerapi.mapper.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper extends ObjectMapper<User, UserRequestDTO, UserResponseDTO> {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Override
    User toEntity(UserRequestDTO request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "orders", ignore = true)
    User toUpdateUser(UserRequestDTO request, @MappingTarget User user);

    @Override
    UserResponseDTO toResponseDTO(User user);

    @Override
    List<UserResponseDTO> toListResponse(List<User> responseList);
}
