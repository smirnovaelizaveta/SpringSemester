package ru.otus.springSemesterBackend.model.solution;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SolutionCheck {
    private List<String> errorLog;
    private boolean correct = false;
}
