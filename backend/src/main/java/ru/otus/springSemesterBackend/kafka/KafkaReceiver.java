package ru.otus.springSemesterBackend.kafka;


import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.otus.springSemesterBackend.model.solution.SolutionCheck;
import ru.otus.springSemesterBackend.services.SolutionService;


@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaReceiver {
    @Autowired
    private final SolutionService solutionService;

    private static final String IN_TOPIC= "solutionCheck";

    @KafkaListener(topics = IN_TOPIC, groupId = "group_id")
    public void process(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Long key, @Payload String solutionCheckJson) {
        SolutionCheck solutionCheck = new Gson().fromJson(solutionCheckJson, SolutionCheck.class);
        solutionService.updateCheckedSolution(key, solutionCheck);
    }
}

