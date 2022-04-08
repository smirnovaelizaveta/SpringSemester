package ru.otus.taskChecker.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SolutionCheck {
    private List<String> errorLog = new ArrayList<>(0);
    private boolean correct = false;

    public void addError(String error) {
        errorLog.add(error);
    }
}
