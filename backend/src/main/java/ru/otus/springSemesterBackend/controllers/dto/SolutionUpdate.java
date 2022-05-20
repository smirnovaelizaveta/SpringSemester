package ru.otus.springSemesterBackend.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SolutionUpdate {
    private Long taskId;
    private Boolean checked;
    private Boolean correct;
    private String stackTrace;
}
