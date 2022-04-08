package ru.otus.springSemesterBackend.model.attempt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.springSemesterBackend.model.attempt.Attempt;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
}
