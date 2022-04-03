package ru.otus.taskChecker.service;

import java.io.File;

public interface MavenService {
    void build(File projectRoot);
}
