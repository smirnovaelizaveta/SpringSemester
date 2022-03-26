package ru.otus.springSemesterBackend.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import ru.otus.springSemesterBackend.domain.user.User;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class UserSolutionStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


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
