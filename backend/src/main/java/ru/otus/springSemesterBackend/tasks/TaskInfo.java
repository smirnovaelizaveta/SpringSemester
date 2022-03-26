package ru.otus.springSemesterBackend.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TaskInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long task_id;
    private String name;
    private String description;
    private Integer difficultylevel;

    public TaskInfo(String name, String description, Integer difficultylevel) {
        this.name = name;
        this.description = description;
        this.difficultylevel = difficultylevel;
    }
}
