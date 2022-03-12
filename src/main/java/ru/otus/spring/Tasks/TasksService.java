package ru.otus.spring.Tasks;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

@RestController

public class TasksService {

    @RequestMapping(path = "tasks", method = RequestMethod.GET)
    public List<Task> getAllTasks() throws IOException {

        List<Task> tasks = Files.walk(Paths.get("src/main/resources/tasks"))
                .filter(Files::isRegularFile)
                .map(path -> new Task(path.toFile()))
                .collect(Collectors.toList());
        return tasks;
    }

    @RequestMapping(path = "task/{taskId}", method = RequestMethod.GET)
    public Task getTask(@PathVariable(name = "taskId") String taskId) throws IOException {
        return new Task(taskId);
    }
}
