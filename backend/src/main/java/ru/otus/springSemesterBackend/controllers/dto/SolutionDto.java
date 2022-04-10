package ru.otus.springSemesterBackend.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.springSemesterBackend.model.solution.Solution;
import ru.otus.springSemesterBackend.model.solution.UserSolutionStatus;

@Data
@AllArgsConstructor
public class SolutionDto {

    private Long taskId;
    private byte[] file;
}
