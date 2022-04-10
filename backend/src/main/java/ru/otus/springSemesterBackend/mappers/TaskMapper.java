package ru.otus.springSemesterBackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Service;
import ru.otus.springSemesterBackend.controllers.dto.TaskDto;
import ru.otus.springSemesterBackend.controllers.dto.UserDto;
import ru.otus.springSemesterBackend.model.solution.UserSolutionStatus;
import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.model.user.User;

@Mapper(componentModel="spring")
public interface TaskMapper {
    @Mappings({
            @Mapping(target = "id", source = "task.id"),
//            @Mapping(target="solution", source = "entity.name"),
//            @Mapping(target="employeeStartDt", source = "entity.startDt",
    })
    TaskDto toDto(Task task, UserSolutionStatus userSolutionStatus);
    Task fromDto(TaskDto taskDto);
}
