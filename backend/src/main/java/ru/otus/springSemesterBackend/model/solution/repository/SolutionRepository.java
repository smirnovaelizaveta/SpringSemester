package ru.otus.springSemesterBackend.model.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springSemesterBackend.model.solution.Solution;
import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.model.user.User;

public interface SolutionRepository extends JpaRepository<Solution, Long> {

    Solution findByTaskAndUser(Task task, User user);
}
