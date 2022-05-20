package ru.otus.springSemesterBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.springSemesterBackend.controllers.dto.ProjectTreeDto;
import ru.otus.springSemesterBackend.controllers.dto.SolutionDto;
import ru.otus.springSemesterBackend.kafka.KafkaSender;
import ru.otus.springSemesterBackend.mappers.SolutionMapper;
import ru.otus.springSemesterBackend.model.solution.Solution;
import ru.otus.springSemesterBackend.model.solution.SolutionCheck;
import ru.otus.springSemesterBackend.model.solution.repository.SolutionRepository;
import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.model.task.repository.TaskRepository;
import ru.otus.springSemesterBackend.model.user.User;
import ru.otus.springSemesterBackend.websocket.NotificationService;

@Service
public class DefaultSolutionService implements SolutionService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private SolutionMapper solutionMapper;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private ProjectTreeService projectTreeService;

    @Autowired
    private KafkaSender kafkaSender;


    @Override
    public void updateCheckedSolution(Long id, SolutionCheck solutionCheck) {
        Solution solution = solutionRepository.findById(id).orElseThrow();
        solution.setCorrect(solutionCheck.isCorrect());
        solution.setChecked(true);
        notificationService.notify(solution);
        solutionRepository.save(solution);
    }

    @Override
    public SolutionDto updateSolution(ProjectTreeDto dto, Long taskId, User user) {
        byte[] code = projectTreeService.convertToZip(dto);
        return updateSolution(code, taskId, user);
    }

    @Override
    public SolutionDto updateSolution(byte[] code, Long taskId, User user) {
        Solution solution = saveZipToSolution(code, taskId, user);
        kafkaSender.send(solution.getId(), code);
        return solutionMapper.toDto(solution);
    }

    private Solution saveZipToSolution(byte[] code, Long taskId, User user) {
        Task task = taskRepository.getById(taskId);
        Solution solution = solutionRepository.findByTaskAndUser(task, user);
        if (solution == null) {
            solution = new Solution();
            solution.setUser(user);
            solution.setTask(task);
        }
        solution.setSolutionCode(code);
        solution.setChecked(false);
        solution.setCorrect(false);

        solutionRepository.save(solution);
        return solution;
    }
}
