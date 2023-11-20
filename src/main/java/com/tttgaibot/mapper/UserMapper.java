package com.tttgaibot.mapper;

import com.tttgaibot.config.MapperConfig;
import com.tttgaibot.dto.request.UserRegistrationDto;
import com.tttgaibot.dto.response.UserResponseDto;
import com.tttgaibot.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto mapToDto(User user);

    @Mapping(target = "id", ignore = true)
    User mapToEntity(UserRegistrationDto userDto);
}
