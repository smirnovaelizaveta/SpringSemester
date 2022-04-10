package ru.otus.springSemesterBackend.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.model.task.repository.TaskRepository;

import java.io.IOException;

@RestController
public class UploadTaskController {

    @Data
    class TaskDto {
        String name;
        String description;
        Integer difficultylevel;
        MultipartFile file;
    }

    @Autowired
    private TaskRepository taskRepository;

    @PostMapping("api/task")
    public void uploadTaskInfo(
            @ModelAttribute TaskDto dto
    ) {
        try {
            byte[] taskCode = dto.file.getInputStream().readAllBytes();
            final Task task = new Task(dto.name, dto.description, dto.difficultylevel, taskCode);
            taskRepository.save(task);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

