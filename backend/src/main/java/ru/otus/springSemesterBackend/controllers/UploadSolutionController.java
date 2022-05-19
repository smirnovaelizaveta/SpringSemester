package ru.otus.springSemesterBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.springSemesterBackend.controllers.dto.ProjectTreeDto;
import ru.otus.springSemesterBackend.kafka.KafkaClient;
import ru.otus.springSemesterBackend.mappers.SolutionMapper;
import ru.otus.springSemesterBackend.model.solution.Solution;
import ru.otus.springSemesterBackend.model.solution.repository.SolutionRepository;
import ru.otus.springSemesterBackend.services.TaskService;

import java.io.IOException;

@RestController
public class UploadSolutionController {

    @Autowired
    private SolutionMapper solutionMapper;

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private KafkaClient kafkaClient;

    @Autowired
    private TaskService taskService;


    @PostMapping("api/task/{taskId}/solution/zip")
    public void uploadFile(@PathVariable(name = "taskId") Long taskId, @RequestBody MultipartFile file) {

        byte[] solutionCode = new byte[0];
        try {
            solutionCode = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Solution solution = new Solution();
        solution.setSolutionCode(solutionCode);
        solutionRepository.save(solution);
        kafkaClient.send(solution.getId(), solutionCode);
//            String text = new String(file.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
//            kafkaTemplate.send("file", "bun", file.getBytes());
    }

    @PostMapping("api/task/{taskId}/solution")
    public ResponseEntity<Solution> check(@PathVariable(name = "taskId") Long taskId, @RequestBody ProjectTreeDto dto) {
        byte[] code = this.taskService.convertToZip(dto);
        Solution solution = new Solution();
        solution.setSolutionCode(code);
        solutionRepository.save(solution);
        kafkaClient.send(solution.getId(), code);
        return ResponseEntity.ok(solution);
    }
}

