package ru.otus.springSemesterBackend.tasks;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.springSemesterBackend.tasks.taskRepository.TaskRepository;
import ru.otus.springSemesterBackend.userService.UserRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class UploadTaskService {

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
        System.out.println("POST called");
        try {
            byte[] taskCode = dto.file.getInputStream().readAllBytes();
            final Task task = new Task(new TaskInfo( dto.name, dto.description, dto.difficultylevel), taskCode);
            taskRepository.save(task);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

