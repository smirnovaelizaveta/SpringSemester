package ru.otus.springSemesterBackend.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.springSemesterBackend.tasks.taskRepository.TaskInfoRepository;
import ru.otus.springSemesterBackend.tasks.taskRepository.TaskRepository;
import ru.otus.springSemesterBackend.userService.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class TasksService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private UserSolutionStatusRepository userSolutionStatusRepository;

    @Autowired
    private TaskInfoRepository taskInfoRepository;

    @RequestMapping(path = "api/task", method = RequestMethod.GET)
    public List<TaskDto> getAllTasks(Principal principal) throws IOException {
        return taskRepository.findAll().stream()
                .map(task -> {
            UserSolutionStatus userSolutionStatus = userSolutionStatusRepository.findByTaskAndUser(task, userRepository.findByUsername(principal.getName()));
                    return (new TaskDto(task.getTaskInfo().getName(), task.getTaskInfo().getDescription(), task.getTaskInfo().getDifficultylevel(), (userSolutionStatus == null) ? UserSolutionStatus.SolutionStatus.NOT_STARTED : userSolutionStatus.getSolutionStatus()));

                            }).collect(Collectors.toList());
    }

    @RequestMapping(path = "api/task/{taskId}", method = RequestMethod.GET)
    public ResponseEntity getTask(@PathVariable(name = "taskId") Long taskId, Principal principal) throws IOException {

        return
                taskRepository.findById(taskId)
                        .map(task -> {
                            UserSolutionStatus userSolutionStatus = userSolutionStatusRepository.findByTaskAndUser(task, userRepository.findByUsername(principal.getName()));
                            return new ResponseEntity<>(new TaskDto(task.getTaskInfo().getName(), task.getTaskInfo().getDescription(), task.getTaskInfo().getDifficultylevel(), (userSolutionStatus == null) ? UserSolutionStatus.SolutionStatus.NOT_STARTED : userSolutionStatus.getSolutionStatus()), HttpStatus.OK);
                        })
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));


    }
}
