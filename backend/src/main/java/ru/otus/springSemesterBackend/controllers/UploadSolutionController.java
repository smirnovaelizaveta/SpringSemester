package ru.otus.springSemesterBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.otus.springSemesterBackend.controllers.dto.ProjectTreeDto;
import ru.otus.springSemesterBackend.controllers.dto.SolutionDto;
import ru.otus.springSemesterBackend.model.user.User;
import ru.otus.springSemesterBackend.services.SolutionService;
import ru.otus.springSemesterBackend.services.UserService;

import java.io.IOException;
import java.security.Principal;

@RestController
public class UploadSolutionController {

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private UserService userService;


    @PostMapping("api/task/{taskId}/solution/zip")
    public ResponseEntity<SolutionDto> uploadFile(@PathVariable(name = "taskId") Long taskId, @RequestParam("file") MultipartFile file, Principal principal) {

        byte[] solutionCode;
        try {
            solutionCode = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = userService.getUser(principal.getName());
        return ResponseEntity.ok(solutionService.updateSolution(solutionCode, taskId, user));
    }

    @PostMapping("api/task/{taskId}/solution")
    public ResponseEntity<SolutionDto> check(@PathVariable(name = "taskId") Long taskId, @RequestBody ProjectTreeDto dto, Principal principal) {
        User user = userService.getUser(principal.getName());
        return ResponseEntity.ok(solutionService.updateSolution(dto, taskId, user));
    }


}

