package ru.otus.springSemesterBackend.tasks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.File;


@Data
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    private String name;
//    private String description;
//    private Integer difficultylevel;

    @OneToOne(targetEntity = TaskInfo.class, cascade = { CascadeType.MERGE, CascadeType.PERSIST }, fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id")
    private TaskInfo taskInfo;
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
}
