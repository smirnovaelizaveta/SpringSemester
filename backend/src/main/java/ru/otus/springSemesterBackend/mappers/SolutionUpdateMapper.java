package ru.otus.springSemesterBackend.mappers;

import org.mapstruct.Mapper;
import ru.otus.springSemesterBackend.model.solution.Solution;
import ru.otus.springSemesterBackend.controllers.dto.SolutionUpdate;

@Mapper(componentModel="spring")
public interface SolutionUpdateMapper {

    SolutionUpdate toDto(Solution solution);

    Solution fromDto(SolutionUpdate solutionUpdate);
}
