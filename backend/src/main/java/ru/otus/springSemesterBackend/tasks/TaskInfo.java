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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(unique = true, nullable = false)
    private Long id;

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "task")
//    @OneToOne(cascade = CascadeType.ALL)
//    private Task task;
    private String name;
    private String description;
    private Integer difficultylevel;

    public TaskInfo(String name, String description, Integer difficultylevel) {
        this.name = name;
        this.description = description;
        this.difficultylevel = difficultylevel;
    }
}
