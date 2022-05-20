package ru.otus.springSemesterBackend.services;

import ru.otus.springSemesterBackend.controllers.dto.ProjectTreeDto;
import ru.otus.springSemesterBackend.controllers.dto.TaskCreationDto;
import ru.otus.springSemesterBackend.controllers.dto.TaskDto;
import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.model.user.User;

import java.util.List;
import java.util.Optional;

public interface TaskService {
    Optional<Task> getTask(Long taskId);

    List<Task> getAllTasks();

    List<TaskDto> getAllTasksForUser(User user);

    Optional<TaskDto> getTaskForUser(Long taskId, User user);

    Optional<ProjectTreeDto> getProjectTree(Long taskId);

    void createTask(TaskCreationDto dto);
}
