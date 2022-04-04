package ru.otus.springSemesterBackend.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springSemesterBackend.domain.user.User;

public interface UserSolutionStatusRepository extends JpaRepository<UserSolutionStatus, Long> {

    UserSolutionStatus findByTaskAndUser(Task task, User user);
}
