package ru.otus.springSemesterBackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.otus.springSemesterBackend.controllers.dto.TaskDto;
import ru.otus.springSemesterBackend.model.solution.Solution;
import ru.otus.springSemesterBackend.model.task.Task;

@Mapper(componentModel="spring")
public interface TaskMapper {
    @Mappings({
            @Mapping(target = "id", source = "task.id"),
            @Mapping(target = "solution", source = "solution")
    })
    TaskDto toDto(Task task, Solution solution);
    Task fromDto(TaskDto taskDto);
}
