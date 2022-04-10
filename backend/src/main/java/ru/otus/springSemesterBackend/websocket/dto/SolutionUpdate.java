package ru.otus.springSemesterBackend.websocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SolutionUpdate {
    Long taskId;
    Boolean correct;
    String stackTrace;
}
