package ru.otus.taskChecker.service;

import ru.otus.taskChecker.model.CheckResult;

public interface SolutionProcessor {
    CheckResult check(byte[] solutionZip);
}
