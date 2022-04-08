package ru.otus.springSemesterBackend.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.springSemesterBackend.kafka.KafkaClient;
import ru.otus.springSemesterBackend.model.attempt.Attempt;
import ru.otus.springSemesterBackend.model.attempt.repository.AttemptRepository;

import java.io.IOException;

@RestController
public class UploadSolutionController {

    @Autowired
    private AttemptRepository attemptRepository;

    @Autowired
    private KafkaClient kafkaClient;


    @PostMapping("api/solution")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        byte[] solution = new byte[0];
        try {
            solution = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Attempt attempt = new Attempt();
        attempt.setSolutionCode(solution);
        attemptRepository.save(attempt);
        kafkaClient.send(attempt.getId(), solution);
//            String text = new String(file.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
//            kafkaTemplate.send("file", "bun", file.getBytes());
    }
}

