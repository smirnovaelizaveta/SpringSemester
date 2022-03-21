package ru.otus.spring.TaskChecker;

import java.io.File;

public class TaskChecker {

    private final File submittedSolution;

    public TaskChecker(File submittedSolution) {
        this.submittedSolution = submittedSolution;
    }
}
