package ru.otus.taskChecker.service;

import ru.otus.taskChecker.model.CheckResult;

import java.util.stream.Stream;

public interface LogChecker {
    CheckResult checkLog(String log);
}
