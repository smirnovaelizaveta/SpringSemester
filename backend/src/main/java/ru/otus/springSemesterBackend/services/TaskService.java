package ru.otus.springSemesterBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.model.task.repository.TaskRepository;
import ru.otus.springSemesterBackend.controllers.dto.ProjectTreeDto;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Optional<Task> getTask(Long taskId) {
        return taskRepository.findById(taskId);
    }

    public java.util.List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Optional<ProjectTreeDto> getProjectTree(@PathVariable(name = "taskId") Long taskId) {
        return getTask(taskId).map(task -> {
            ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(task.getTaskCode()));
            ProjectTreeDto.Builder builder = new ProjectTreeDto.Builder();
            ProjectTreeDto.ProjectFile projectFile;
            while ((projectFile = getNextProjectFile(zipInputStream)) != null) {
//                System.out.println(projectFile.getName());
                                builder.add(projectFile);
            }
            return builder.build();
        });
    }

    private ProjectTreeDto.ProjectFile getNextProjectFile(ZipInputStream zipInputStream) {
        try {
            ZipEntry entry = zipInputStream.getNextEntry();
            if(entry == null) {
                return null;
            }
            String name = entry.getName();
            String content = entry.isDirectory()? "" : new String(zipInputStream.readAllBytes());
            boolean readonly = entry.isDirectory() || name.contains("/test/");
            zipInputStream.closeEntry();
            return new ProjectTreeDto.ProjectFile(name, content, new ArrayList<>(), readonly);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
