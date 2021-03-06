package ru.otus.taskChecker.kafka;

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
import ru.otus.taskChecker.model.SolutionCheck;
import ru.otus.taskChecker.service.SolutionProcessor;


@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaClient {
    private final SolutionProcessor solutionProcessor;
    private final KafkaTemplate<Long, String> kafkaTemplate;

    private static final String IN_TOPIC= "solution";
    private static final String OUT_TOPIC = "solutionCheck";

    @KafkaListener(topics = IN_TOPIC, groupId = "group_id")
    public void process(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Long key, @Payload byte[] solutionZip) {
        SolutionCheck result = solutionProcessor.check(solutionZip);
        String message = new Gson().toJson(result);
        kafkaTemplate.send(OUT_TOPIC, key, message);

    }
}
