package ru.otus.springSemesterBackend.userService;

import org.springframework.stereotype.Service;
import ru.otus.springSemesterBackend.model.user.User;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void insert(User user) {
        userRepository.save(user);
    }
}
