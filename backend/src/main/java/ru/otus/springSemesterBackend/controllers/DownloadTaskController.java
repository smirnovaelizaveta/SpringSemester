package ru.otus.springSemesterBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.services.TaskService;
import ru.otus.springSemesterBackend.controllers.dto.ProjectTreeDto;

import javax.servlet.http.HttpServletResponse;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class DownloadTaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping(path = "api/task/{taskId}/zip")
    public ResponseEntity<StreamingResponseBody> getProjectZip(HttpServletResponse response,
                                                               @PathVariable(name = "taskId") Long taskId) {
        Task task = taskService.getTask(taskId)
                .orElseThrow(()->new ResponseStatusException(NOT_FOUND, "Unable to find resource"));

        StreamingResponseBody stream = out -> {
            out.write(task.getTaskCode());
        };

        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=" + task.getName()+".zip");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "0");

        return ResponseEntity.ok(stream);
    }

    @RequestMapping(path = "api/task/{taskId}/project-tree")
    public ProjectTreeDto getProjectTree(@PathVariable(name = "taskId") Long taskId) {
        return this.taskService.getProjectTree(taskId)
                        .orElseThrow(()->new ResponseStatusException(NOT_FOUND, "Unable to find resource"));


    }

}
