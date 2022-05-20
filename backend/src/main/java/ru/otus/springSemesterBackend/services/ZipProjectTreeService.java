package ru.otus.springSemesterBackend.services;

import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.springSemesterBackend.controllers.dto.ProjectTreeDto;
import ru.otus.springSemesterBackend.model.task.Task;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@Service
public class ZipProjectTreeService implements ProjectTreeService {
    public ProjectTreeDto getProjectTree(Task task) {
        ZipInputStream zipInputStream = new ZipInputStream(new ByteArrayInputStream(task.getTaskCode()));
        ProjectTreeDto.Builder builder = new ProjectTreeDto.Builder();
        ProjectTreeDto.ProjectFile projectFile;
        while ((projectFile = getNextProjectFile(zipInputStream)) != null) {
//                System.out.println(projectFile.getName());
            builder.add(projectFile);
        }
        return builder.build();
    }

    @SneakyThrows
    public byte[] convertToZip(ProjectTreeDto dto) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        Deque<ProjectTreeDto.ProjectFile> stack = new ArrayDeque<>(dto.getFiles());

        while(!stack.isEmpty()) {
            ProjectTreeDto.ProjectFile file = stack.pop();
            ZipEntry zipEntry = new ZipEntry(file.getName());
            try {
                zipOutputStream.putNextEntry(zipEntry);
                zipOutputStream.write(file.getContent().getBytes());
//                zipOutputStream.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stack.addAll(file.getChildren());
        }
        zipOutputStream.close();

        byte[] bytes = outputStream.toByteArray();
        File file = new File("packed.zip");
        try {
            file.createNewFile();
            Files.write(file.toPath(), bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bytes;
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
