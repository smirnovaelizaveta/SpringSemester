package ru.otus.springSemesterBackend.tasks.taskRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springSemesterBackend.domain.user.User;
import ru.otus.springSemesterBackend.tasks.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
