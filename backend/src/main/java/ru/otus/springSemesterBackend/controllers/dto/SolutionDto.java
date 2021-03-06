package ru.otus.springSemesterBackend.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SolutionDto {
    private Long id;
    private boolean checked;
    private boolean correct;
    private String stackTrace;
}
