package ru.otus.springSemesterBackend.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.otus.springSemesterBackend.model.attempt.Attempt;

@Data
@AllArgsConstructor
public class SolutionUpdate {
    Long taskId;
    Boolean correct;
    String stackTrace;
}
