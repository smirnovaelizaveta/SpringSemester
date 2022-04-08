package ru.otus.springSemesterBackend.model.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;


@Data
@NoArgsConstructor
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private Integer difficultylevel;
    @Column(columnDefinition="blob")
    private byte[] taskCode;

    public Task(String name, String description, Integer difficultylevel, byte[] taskCode) {
        this.name = name;
        this.description = description;
        this.difficultylevel = difficultylevel;
        this.taskCode = taskCode;
    }
}
