package ru.otus.springSemesterBackend.tasks;

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
//    @Column(unique = true, nullable = false)
    private Long id;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    private TaskInfo taskInfo;
//    private String name;
//    private String description;
//    private Integer difficultylevel;

//    @OneToOne(targetEntity = TaskInfo.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
//    @JoinColumn(name = "task_id")
//    private TaskInfo taskInfo;
    @Column(columnDefinition="blob")
    private byte[] taskCode;





//    private final String taskCodeFilePath =
//            "src/main/resources/tasks/task%1$s.zip";
////        "tasks/task%1$s.zip";
//    private File taskCodeFile;

//    public Task(String taskId) {
//        this.taskId = taskId;
//        this.taskCodeFile = new File(String.format(taskCodeFilePath, taskId));
//    }
//
//    public Task(File taskCodeFile) {
//        this.taskId = taskCodeFile.getName().replaceAll("\\D+","");
//        this.taskCodeFile = taskCodeFile;
//    }


    public Task(TaskInfo taskInfo, byte[] taskCode) {
        this.taskInfo = taskInfo;
        this.taskCode = taskCode;
    }

//    public Task(String name, String description, Integer difficultylevel, byte[] taskCode) {
//        this.name = name;
//        this.description = description;
//        this.difficultylevel = difficultylevel;
//    }
}
