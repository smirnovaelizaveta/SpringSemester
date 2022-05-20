package ru.otus.springSemesterBackend.services;

import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.springSemesterBackend.controllers.dto.ProjectTreeDto;
import ru.otus.springSemesterBackend.model.task.Task;

import java.util.Optional;

public interface ProjectTreeService {
    ProjectTreeDto getProjectTree(Task task);
    byte[] convertToZip(ProjectTreeDto dto);
}
