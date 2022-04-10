package ru.otus.springSemesterBackend.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.springSemesterBackend.model.solution.Solution;
import ru.otus.springSemesterBackend.model.solution.UserSolutionStatus;

@Data
@AllArgsConstructor
public class TaskDto {

    private Long id;
    private String name;
    private String description;
    private Integer difficultylevel;
    private UserSolutionStatus.SolutionStatus solutionStatus;
    private Solution solution;
}
