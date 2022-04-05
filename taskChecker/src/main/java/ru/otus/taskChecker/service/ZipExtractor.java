package ru.otus.taskChecker.service;

import java.io.File;
import java.nio.file.Path;

public interface ZipExtractor {
    File extract(byte[] zip);
}
