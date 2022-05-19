package ru.otus.springSemesterBackend.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import ru.otus.springSemesterBackend.mappers.SolutionUpdateMapper;
import ru.otus.springSemesterBackend.model.solution.Solution;
import ru.otus.springSemesterBackend.model.user.User;
import ru.otus.springSemesterBackend.controllers.dto.SolutionUpdate;

@Service
public class WebSocketNotificationService implements NotificationService {
    private static final String destination = "/topic/solution";

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private SolutionUpdateMapper solutionUpdateMapper;

    @Override
    public void notify(Solution solution) {
        this.send(solutionUpdateMapper.toDto(solution), solution.getUserSolutionStatus().getUser());
    }

    public void send(SolutionUpdate solutionUpdate, User user) {
        System.out.println("sending " + solutionUpdate );
        this.simpMessagingTemplate.convertAndSendToUser(user.getUsername(), destination, solutionUpdate);
    }
}
