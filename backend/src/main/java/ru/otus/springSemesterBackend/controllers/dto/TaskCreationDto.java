package ru.otus.springSemesterBackend.controllers.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TaskCreationDto {
    String name;
    String description;
    Integer difficultylevel;
    MultipartFile file;
}
