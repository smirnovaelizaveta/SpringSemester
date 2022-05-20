package ru.otus.springSemesterBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.springSemesterBackend.controllers.dto.TaskDto;
import ru.otus.springSemesterBackend.mappers.TaskMapper;
import ru.otus.springSemesterBackend.model.solution.Solution;
import ru.otus.springSemesterBackend.model.solution.repository.SolutionRepository;
import ru.otus.springSemesterBackend.services.TaskService;
import ru.otus.springSemesterBackend.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskMapper taskMapper;

    @RequestMapping(path = "api/task", method = RequestMethod.GET)
    public List<TaskDto> getAllTasks(Principal principal) {
        return taskService.getAllTasks().stream()
                .map(task -> {
                    Solution solution = solutionRepository.findByTaskAndUser(task, userService.getUser(principal.getName()));
                    return taskMapper.toDto(task, solution);
                }).collect(Collectors.toList());
    }

    @RequestMapping(path = "api/task/{taskId}", method = RequestMethod.GET)
    public ResponseEntity getTask(@PathVariable(name = "taskId") Long taskId, @AuthenticationPrincipal Principal principal) {
        String userName = (principal.getName() != null) ? principal.getName() : "anonymous";

        return
                taskService.getTask(taskId)
                        .map(task -> {
                            Solution solution = solutionRepository.findByTaskAndUser(task, userService.getUser(principal.getName()));
                            return new ResponseEntity<>(taskMapper.toDto(task, solution), HttpStatus.OK);
                        })
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));


    }
}
