package ru.otus.springSemesterBackend.model.solution;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.springSemesterBackend.model.task.Task;
import ru.otus.springSemesterBackend.model.user.User;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Solution {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean correct;

    @Column(columnDefinition="blob")
    private byte[] solutionCode;

    private String stackTrace;

    @ManyToOne(targetEntity = Task.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne(targetEntity = User.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    private SolutionStatus solutionStatus;

    public enum SolutionStatus {
        NOT_STARTED,
        IN_PROGRESS,
        SOLVED;
    }
}
