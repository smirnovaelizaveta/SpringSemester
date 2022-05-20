package ru.otus.springSemesterBackend.model.task;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Integer difficultyLevel;
    @Column(columnDefinition="blob")
    private byte[] taskCode;

    public Task(String name, String description, Integer difficultyLevel, byte[] taskCode) {
        this.name = name;
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.taskCode = taskCode;
    }
}
