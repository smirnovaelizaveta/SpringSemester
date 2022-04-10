package ru.otus.springSemesterBackend.services;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.otus.springSemesterBackend.model.solution.UserSolutionStatus;
import ru.otus.springSemesterBackend.model.solution.repository.UserSolutionStatusRepository;
import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.model.task.repository.TaskRepository;
import ru.otus.springSemesterBackend.model.user.User;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.nio.file.Path;
import java.util.Optional;

@Service
public class UserSolutionStatusService {

    @Autowired
    private UserSolutionStatusRepository userSolutionStatusRepository;

    public UserSolutionStatus findByTaskAndUser(Task task, User user)
    {return userSolutionStatusRepository.findByTaskAndUser(task, user);}

}
