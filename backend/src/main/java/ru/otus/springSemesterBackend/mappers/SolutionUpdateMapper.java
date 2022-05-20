package ru.otus.springSemesterBackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.otus.springSemesterBackend.controllers.dto.UserDto;
import ru.otus.springSemesterBackend.model.solution.Solution;
import ru.otus.springSemesterBackend.model.user.User;
import ru.otus.springSemesterBackend.websocket.dto.SolutionUpdate;

@Mapper(componentModel="spring")
public interface SolutionUpdateMapper {

    @Mappings(@Mapping(target="taskId", source = "task.id"))
    SolutionUpdate toDto(Solution solution);

    Solution fromDto(SolutionUpdate solutionUpdate);
}
