package ru.otus.springSemesterBackend.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;
import ru.otus.springSemesterBackend.model.attempt.Attempt;
import ru.otus.springSemesterBackend.websocket.dto.SolutionUpdate;

@Service
public class WebSocketNotificationService implements NotificationService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @Override
    public void notify(Attempt attempt) {
        this.send(new SolutionUpdate(attempt.getUserSolutionStatus().getTask().getId(), attempt));
    }

    public void send(SolutionUpdate solutionUpdate) {
        System.out.println("sending " + solutionUpdate );
        this.simpMessagingTemplate.convertAndSend("/topic/greetings", solutionUpdate.getSolution().getStackTrace());
    }
}
