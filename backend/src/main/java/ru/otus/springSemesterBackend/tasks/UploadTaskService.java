package ru.otus.springSemesterBackend.tasks;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.springSemesterBackend.tasks.taskRepository.TaskInfoRepository;
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

    @Autowired
    private TaskInfoRepository taskInfoRepository;

    @PostMapping("api/task")
    public void uploadTaskInfo(
            @ModelAttribute TaskDto dto
    ) {
        try {
            byte[] taskCode = dto.file.getInputStream().readAllBytes();
            TaskInfo taskInfo = new TaskInfo(dto.name, dto.description, dto.difficultylevel);
            taskInfoRepository.save(taskInfo);
            final Task task = new Task(taskInfo, taskCode);
            taskRepository.save(task);
//            taskInfo.setTask_id(task.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

