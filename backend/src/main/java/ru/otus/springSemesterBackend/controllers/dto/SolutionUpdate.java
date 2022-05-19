package ru.otus.springSemesterBackend.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SolutionUpdate {
    Long taskId;
    Boolean checked;
    Boolean correct;
    String stackTrace;
}
