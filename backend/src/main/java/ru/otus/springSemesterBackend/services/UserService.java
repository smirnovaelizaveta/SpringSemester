package ru.otus.springSemesterBackend.services;

import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.model.user.User;

public interface UserService {
    User getUser(String userName);

    void insert(User user);
}
