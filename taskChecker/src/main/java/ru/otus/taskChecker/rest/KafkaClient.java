package ru.otus.taskChecker.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
public class KafkaClient {
    private static final String TOPIC = "my-topic";

    private final List<String> CONSUMED_MESSAGES = new ArrayList<>();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @KafkaListener(topics = TOPIC, groupId = "group_id")
    public void consume(String m) {
        log.info("Message consumed: {}", m);
        CONSUMED_MESSAGES.add(m);
    }

    @GetMapping("/pub/{m}")
    public void produce(@PathVariable String m) {
        log.info("Message produced: {}", m);
        kafkaTemplate.send(TOPIC, m);
    }

    @GetMapping("/get")
    public List<String> get() {
        log.info("Get consumed messages");
        List<String> l = new ArrayList<>(CONSUMED_MESSAGES);
        CONSUMED_MESSAGES.clear();
        return l;
    }
}
