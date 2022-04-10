package ru.otus.springSemesterBackend.websocket;

import ru.otus.springSemesterBackend.model.solution.Solution;

public interface NotificationService {
    void notify(Solution solution);
}
