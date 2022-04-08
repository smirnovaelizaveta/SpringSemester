package ru.otus.taskChecker.service;

import ru.otus.taskChecker.model.SolutionCheck;

public interface SolutionProcessor {
    SolutionCheck check(byte[] solutionZip);
}
