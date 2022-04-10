package ru.otus.springSemesterBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.springSemesterBackend.controllers.dto.SolutionDto;
import ru.otus.springSemesterBackend.kafka.KafkaClient;
import ru.otus.springSemesterBackend.mappers.SolutionMapper;
import ru.otus.springSemesterBackend.model.solution.Solution;
import ru.otus.springSemesterBackend.model.solution.repository.SolutionRepository;

import java.io.IOException;
import java.security.Principal;

@RestController
public class UploadSolutionController {

    @Autowired
    private SolutionMapper solutionMapper;

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private KafkaClient kafkaClient;


    @PostMapping("api/task/{taskId}/solution")
    public void uploadFile(@PathVariable(name = "taskId") Long taskId, @RequestParam("file") MultipartFile file) {

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
}

