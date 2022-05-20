package ru.otus.springSemesterBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springSemesterBackend.model.solution.Solution;
import ru.otus.springSemesterBackend.model.solution.repository.SolutionRepository;
import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.model.user.User;

@Service
public class SolutionService {

    @Autowired
    private SolutionRepository solutionRepository;

    public Solution findByTaskAndUser(Task task, User user)
    {return solutionRepository.findByTaskAndUser(task, user);}

    public void save(Solution solution) {
        solutionRepository.save(solution);
    }
}
