package ru.otus.springSemesterBackend.tasks.taskRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springSemesterBackend.tasks.Task;
import ru.otus.springSemesterBackend.tasks.TaskInfo;

public interface TaskInfoRepository extends JpaRepository<TaskInfo, Long> {
}
