package ru.otus.springSemesterBackend.services;

import ru.otus.springSemesterBackend.controllers.dto.ProjectTreeDto;
import ru.otus.springSemesterBackend.controllers.dto.SolutionDto;
import ru.otus.springSemesterBackend.model.solution.SolutionCheck;
import ru.otus.springSemesterBackend.model.user.User;

public interface SolutionService {
    void updateCheckedSolution(Long id, SolutionCheck solutionCheck);

    SolutionDto updateSolution(ProjectTreeDto dto, Long taskId, User user);

    SolutionDto updateSolution(byte[] code, Long taskId, User user);
}
