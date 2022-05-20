package ru.otus.springSemesterBackend.services;

import org.springframework.stereotype.Service;
import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.model.user.User;
import ru.otus.springSemesterBackend.model.user.repository.UserRepository;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public void insert(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String name) {
        return userRepository.findByUsername(name);
    }
}
