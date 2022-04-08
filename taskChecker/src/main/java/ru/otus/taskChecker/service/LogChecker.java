package ru.otus.taskChecker.service;

import ru.otus.taskChecker.model.SolutionCheck;

public interface LogChecker {
    SolutionCheck checkLog(String log);
}
