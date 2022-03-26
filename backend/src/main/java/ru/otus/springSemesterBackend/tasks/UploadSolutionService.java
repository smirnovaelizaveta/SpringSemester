package ru.otus.springSemesterBackend.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.springSemesterBackend.userService.UserRepository;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RestController
public class UploadSolutionService {

    @Autowired
    private KafkaTemplate<String, byte[]> kafkaTemplate;


    @PostMapping("api/solution")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String text = new String(file.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            kafkaTemplate.send("file", "bun", file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

