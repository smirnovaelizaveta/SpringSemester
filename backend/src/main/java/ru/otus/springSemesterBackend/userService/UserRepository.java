package ru.otus.springSemesterBackend.userService;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springSemesterBackend.model.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
