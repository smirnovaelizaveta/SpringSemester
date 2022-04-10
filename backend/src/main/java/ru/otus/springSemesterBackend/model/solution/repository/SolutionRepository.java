package ru.otus.springSemesterBackend.model.solution.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springSemesterBackend.model.solution.Solution;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
}
