package ru.otus.springSemesterBackend.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.springSemesterBackend.model.solution.Solution;

@Data
@AllArgsConstructor
public class TaskDto {

    private Long id;
    private String name;
    private String description;
    private Integer difficultylevel;
    private Solution solution;
}
