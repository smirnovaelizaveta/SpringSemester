package ru.otus.springSemesterBackend.model.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springSemesterBackend.model.task.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
