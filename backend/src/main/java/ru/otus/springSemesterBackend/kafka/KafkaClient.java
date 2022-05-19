package ru.otus.springSemesterBackend.kafka;


import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.otus.springSemesterBackend.model.solution.SolutionCheck;
import ru.otus.springSemesterBackend.model.solution.Solution;
import ru.otus.springSemesterBackend.model.solution.repository.SolutionRepository;
import ru.otus.springSemesterBackend.websocket.NotificationService;

import java.util.Optional;


@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaClient {
    @Autowired
    private final KafkaTemplate<Long, byte[]> kafkaTemplate;

    @Autowired
    private final SolutionRepository solutionRepository;

    @Autowired
    private final NotificationService notificationService;

    private static final String IN_TOPIC= "solutionCheck";
    private static final String OUT_TOPIC = "solution";

    @KafkaListener(topics = IN_TOPIC, groupId = "group_id")
    public void process(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Long key, @Payload String solutionCheckJson) {
        System.out.println("RECEIVED_MESSAGE_KEY is "+key);
        System.out.println("Body is "+solutionCheckJson);
        SolutionCheck solutionCheck = new Gson().fromJson(solutionCheckJson, SolutionCheck.class);
//        CheckResult result = solutionProcessor.check(solutionZip);
//        String message = new Gson().toJson(result);
        Optional<Solution> solutionOptional = solutionRepository.findById(key);
        Solution solution = solutionOptional.get();
        System.out.println("lol"+ solution.isCorrect());

        solution.setCorrect(solutionCheck.isCorrect());
        solution.setChecked(true);
        notificationService.notify(solution);
        solutionRepository.save(solution);
//        kafkaTemplate.send(OUT_TOPIC, key, message);

    }
    public void send(Long key, byte[] solutionZip) {
        kafkaTemplate.send(OUT_TOPIC, key, solutionZip);
    }
}

