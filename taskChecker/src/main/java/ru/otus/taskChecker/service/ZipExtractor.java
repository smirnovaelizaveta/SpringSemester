package ru.otus.taskChecker.service;

import java.io.File;

public interface ZipExtractor {
    File extract(byte[] zip);
}
