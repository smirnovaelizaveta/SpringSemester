package ru.otus.spring.userService;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.spring.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
