package ru.otus.springSemesterBackend.userService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.springSemesterBackend.domain.user.User;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void insert(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
