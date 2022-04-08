package ru.otus.springSemesterBackend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.springSemesterBackend.tasks.UserSolutionStatus;

@Data
@AllArgsConstructor
public class TaskDto {

    private Long id;
    private String name;
    private String description;
    private Integer difficultylevel;
    private UserSolutionStatus.SolutionStatus solutionStatus;
}
