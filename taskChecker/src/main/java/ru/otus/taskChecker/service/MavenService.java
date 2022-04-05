package ru.otus.taskChecker.service;

import java.io.File;
import java.nio.file.Path;

public interface MavenService {
    void build(File projectRoot);
}
