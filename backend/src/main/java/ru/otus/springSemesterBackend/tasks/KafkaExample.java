package ru.otus.springSemesterBackend.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("msg")
public class KafkaExample {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping
    public void sendOrder(String msgId, String msg){
        kafkaTemplate.send("msg", msgId, msg);
    }

    @GetMapping
    public String test(){
        return "test";
    }
}
