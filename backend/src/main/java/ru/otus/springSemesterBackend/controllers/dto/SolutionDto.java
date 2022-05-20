package ru.otus.springSemesterBackend.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SolutionDto {

    private Long taskId;
    private byte[] file;
}
