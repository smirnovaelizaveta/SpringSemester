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
import ru.otus.springSemesterBackend.services.SolutionService;


@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaSender {
    @Autowired
    private final KafkaTemplate<Long, byte[]> kafkaTemplate;

    private static final String OUT_TOPIC = "solution";

    public void send(Long key, byte[] solutionZip) {
        kafkaTemplate.send(OUT_TOPIC, key, solutionZip);
    }
}

