package ru.otus.springSemesterBackend.controllers;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.springSemesterBackend.controllers.dto.TaskCreationDto;
import ru.otus.springSemesterBackend.controllers.dto.TaskDto;
import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.model.task.repository.TaskRepository;
import ru.otus.springSemesterBackend.services.TaskService;

import java.io.IOException;

@RestController
public class UploadTaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("api/task")
    public void uploadTaskInfo(@ModelAttribute TaskCreationDto dto) {
        taskService.createTask(dto);
    }
}

