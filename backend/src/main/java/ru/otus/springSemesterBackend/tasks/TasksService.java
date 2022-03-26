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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController

public class TasksService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskInfoRepository taskInfoRepository;

    @RequestMapping(path = "api/task", method = RequestMethod.GET)
    public List<TaskInfo> getAllTasks() throws IOException {
        return taskInfoRepository.findAll();
    }

    @RequestMapping(path = "api/task/{taskId}", method = RequestMethod.GET)
    public ResponseEntity getTask(@PathVariable(name = "taskId") Long taskId) throws IOException {

        return taskRepository.findById(taskId)
                .map(result -> new ResponseEntity<>(result, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
