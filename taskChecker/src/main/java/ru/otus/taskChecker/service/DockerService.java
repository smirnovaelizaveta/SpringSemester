package ru.otus.taskChecker.service;

import java.io.File;
import java.util.stream.Stream;

public interface DockerService {
    String runAndGetLog(File projectRoot);
}
