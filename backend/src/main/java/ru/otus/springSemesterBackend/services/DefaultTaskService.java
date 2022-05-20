package ru.otus.springSemesterBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springSemesterBackend.controllers.dto.ProjectTreeDto;
import ru.otus.springSemesterBackend.controllers.dto.TaskCreationDto;
import ru.otus.springSemesterBackend.controllers.dto.TaskDto;
import ru.otus.springSemesterBackend.mappers.TaskMapper;
import ru.otus.springSemesterBackend.model.solution.repository.SolutionRepository;
import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.model.task.repository.TaskRepository;
import ru.otus.springSemesterBackend.model.user.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultTaskService implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private ProjectTreeService projectTreeService;

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public Optional<Task> getTask(Long taskId) {
        return taskRepository.findById(taskId);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<TaskDto> getAllTasksForUser(User user) {
        return getAllTasks().stream()
            .map(task -> this.fillSolution(task, user))
            .collect(Collectors.toList());
    }

    @Override
    public Optional<TaskDto> getTaskForUser(Long taskId, User user) {
        return getTask(taskId).map(task -> fillSolution(task, user));
    }

    @Override
    public Optional<ProjectTreeDto> getProjectTree(Long taskId) {
        return taskRepository.findById(taskId).map(projectTreeService::getProjectTree);
    }

    @Override
    public void createTask(TaskCreationDto dto) {
        final Task task = taskMapper.fromDto(dto);
        taskRepository.save(task);
    }

    private TaskDto fillSolution(Task task, User user) {
        return taskMapper.toDto(task, solutionRepository.findByTaskAndUser(task, user));
    }
}
