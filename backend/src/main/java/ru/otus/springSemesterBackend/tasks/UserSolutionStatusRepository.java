package ru.otus.springSemesterBackend.tasks;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.model.user.User;

public interface UserSolutionStatusRepository extends JpaRepository<UserSolutionStatus, Long> {

    UserSolutionStatus findByTaskAndUser(Task task, User user);
}
