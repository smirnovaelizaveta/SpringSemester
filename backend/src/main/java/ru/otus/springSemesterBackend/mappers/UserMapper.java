package ru.otus.springSemesterBackend.mappers;

import org.mapstruct.Mapper;
import ru.otus.springSemesterBackend.controllers.dto.UserDto;
import ru.otus.springSemesterBackend.model.user.User;

@Mapper(componentModel="spring")
public interface UserMapper {

    UserDto toDto(User user);
}
