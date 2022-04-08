package ru.otus.springSemesterBackend.model.attempt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.springSemesterBackend.tasks.UserSolutionStatus;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Attempt {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private UserSolutionStatus userSolutionStatus;

    private boolean correct;

    @Column(columnDefinition="blob")
    private byte[] solutionCode;

    private String stackTrace;

}
