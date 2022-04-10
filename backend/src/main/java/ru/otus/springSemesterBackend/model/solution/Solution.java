package ru.otus.springSemesterBackend.model.solution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.springSemesterBackend.model.solution.UserSolutionStatus;
import ru.otus.springSemesterBackend.model.task.Task;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UserSolutionStatus userSolutionStatus;

    private boolean correct;

    @Column(columnDefinition="blob")
    private byte[] solutionCode;

    private String stackTrace;

    @ManyToOne
    private Task task;

}
