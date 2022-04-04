package ru.otus.springSemesterBackend.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class TaskDto {

    private String name;
    private String description;
    private Integer difficultylevel;
    private UserSolutionStatus.SolutionStatus solutionStatus;
}
