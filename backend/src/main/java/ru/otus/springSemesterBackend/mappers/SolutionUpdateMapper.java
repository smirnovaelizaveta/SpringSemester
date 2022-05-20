package ru.otus.springSemesterBackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.otus.springSemesterBackend.controllers.dto.SolutionUpdate;
import ru.otus.springSemesterBackend.model.solution.Solution;

@Mapper(componentModel="spring")
public interface SolutionUpdateMapper {

    @Mappings(@Mapping(target="taskId", source = "task.id"))
    SolutionUpdate toDto(Solution solution);
}
