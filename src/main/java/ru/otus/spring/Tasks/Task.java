package ru.otus.spring.Tasks;

import java.io.File;
import java.nio.file.Path;

public class Task {

    private final String taskId;
    private String taskName;
    private String taskDescription;

    private final String taskCodeFilePath =
            "src/main/resources/tasks/task%1$s.zip";
//        "tasks/task%1$s.zip";
    private final File taskCodeFile;

    public Task(String taskId) {
        this.taskId = taskId;
        this.taskCodeFile = new File(String.format(taskCodeFilePath, taskId));
    }

    public Task(File taskCodeFile) {
        this.taskId = taskCodeFile.getName().replaceAll("\\D+","");
        this.taskCodeFile = taskCodeFile;
    }

    public String getTaskId() {
        return taskId;
    }

    public File getTaskCodeFile() {
        return taskCodeFile;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }
}
