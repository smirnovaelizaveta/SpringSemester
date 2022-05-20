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
import ru.otus.springSemesterBackend.services.SolutionService;
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
    private UserService userService;

    @RequestMapping(path = "api/task", method = RequestMethod.GET)
    public List<TaskDto> getAllTasks(Principal principal) {
        return taskService.getAllTasksForUser(userService.getUser(principal.getName()));
    }

    @RequestMapping(path = "api/task/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<TaskDto> getTask(@PathVariable(name = "taskId") Long taskId, @AuthenticationPrincipal Principal principal) {
        return ResponseEntity.of(taskService.getTaskForUser(taskId, userService.getUser(principal.getName())));
    }
}
