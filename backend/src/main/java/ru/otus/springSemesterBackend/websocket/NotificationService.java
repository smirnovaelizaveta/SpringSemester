package ru.otus.springSemesterBackend.websocket;

import ru.otus.springSemesterBackend.model.attempt.Attempt;

public interface NotificationService {
    void notify(Attempt attempt);
}
