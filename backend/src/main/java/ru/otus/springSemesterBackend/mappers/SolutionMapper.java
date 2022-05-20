package ru.otus.springSemesterBackend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import ru.otus.springSemesterBackend.controllers.dto.SolutionDto;
import ru.otus.springSemesterBackend.model.solution.Solution;

@Mapper(componentModel="spring")
public interface SolutionMapper {
    SolutionDto toDto(Solution solution);
}
