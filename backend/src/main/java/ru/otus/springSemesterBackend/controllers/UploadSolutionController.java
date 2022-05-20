package ru.otus.springSemesterBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.springSemesterBackend.kafka.KafkaClient;
import ru.otus.springSemesterBackend.mappers.SolutionMapper;
import ru.otus.springSemesterBackend.model.solution.Solution;

import ru.otus.springSemesterBackend.model.solution.repository.SolutionRepository;
import ru.otus.springSemesterBackend.model.task.repository.TaskRepository;
import ru.otus.springSemesterBackend.model.user.repository.UserRepository;
import ru.otus.springSemesterBackend.services.SolutionService;
import ru.otus.springSemesterBackend.services.TaskService;
import ru.otus.springSemesterBackend.services.UserService;

import java.io.IOException;
import java.security.Principal;

@RestController
public class UploadSolutionController {

    @Autowired
    private SolutionMapper solutionMapper;

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private KafkaClient kafkaClient;


    @PostMapping("api/task/{taskId}/solution")
    public void uploadFile(@PathVariable(name = "taskId") Long taskId, @RequestParam("file") MultipartFile file, Principal principal) {

        byte[] solutionCode = new byte[0];
        try {
            solutionCode = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Solution solution = solutionService.findByTaskAndUser(taskService.getById(taskId), userService.findByUsername(principal.getName()));
        if (solution == null) {
            solution = new Solution();
            solution.setUser(userService.findByUsername(principal.getName()));
            solution.setTask(taskService.getById(taskId));
        }
        solution.setSolutionCode(solutionCode);

        solutionService.save(solution);
        kafkaClient.send(solution.getId(), solutionCode);
    }
}

