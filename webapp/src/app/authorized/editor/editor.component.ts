import { Component, OnInit, AfterViewInit, ViewChild, Input } from '@angular/core';
import { TasksService } from '../service/tasks.service';
import { Status, Task } from '../model/task';
import { Project, ProjectFile } from '../model/project';
import { NestedTreeControl } from '@angular/cdk/tree';
import { MatTreeNestedDataSource } from '@angular/material/tree';
import { ActivatedRoute, Params } from '@angular/router';
import { zip, Observable, of } from 'rxjs';
import { map, tap, flatMap } from 'rxjs/operators';


@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  public Status: any = Status;

  tasks?: Observable<Task[]>;
  selectedTask?: Task
  selectedFile: ProjectFile | undefined
  treeControl = new NestedTreeControl<ProjectFile>(node => node.children);
  dataSource = new MatTreeNestedDataSource<ProjectFile>();
  hasChild = (_: number, node: ProjectFile) => !!node.children && node.children.length > 0;


  constructor(
    private tasksService: TasksService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.tasks = this.tasksService.getTasks();
    this.tasks.subscribe(
      tasks => this.route.params .subscribe(params  => {
          const taskId: number = +params['taskId'];
          console.log(tasks, taskId)
          this.selectedTask = tasks.find((task: Task) => task.id === taskId);
          if(this.selectedTask) {
            this.tasksService.getProjectTree(this.selectedTask.id)
            .subscribe(
              project => {
                console.log(this.tasks, this.selectedTask);
                if(project) {
                  const data = project.files;
                  this.dataSource.data = data;
                  this.treeControl.dataNodes = data;
                  this.treeControl.expandAll();
                }
              }
            ) 
       }
      })
    )
  }      
  

    // this.tasksService.
  

  @ViewChild('tree') tree: any;

  selectFile(file: ProjectFile) {
    this.selectedFile = file;
  }

  download(format: String = "zip"): void {
    this.tasksService.downloadCode(this.selectedTask!.id)
      .subscribe(([task, response]) => {
          let dataType = response.type;
          let binaryData = [];
          binaryData.push(response);
          let downloadLink = document.createElement('a');
          downloadLink.href = window.URL.createObjectURL(new Blob(binaryData, { type: dataType }));
          downloadLink.setAttribute('download', `task${task!.id}.${format}`);
          document.body.appendChild(downloadLink);
          downloadLink.click();
        });
  }

  upload(event: any) {
    const file: File = event.target.files[0];

    if (file) {
      const formData = new FormData();
      formData.append("file", file);
      this.tasksService.uploadCode(formData).subscribe();
    }
  }

  getSelectedTask(): Observable<any> {
    return zip(this.tasksService.getTasks(), this.route.params)
      .pipe(
        map(([tasks, params]) => {
          console.log(tasks, params);
          const taskId: number = +params['taskId'];
          return tasks?.find(task => task.id === taskId);
        })
      )
  }
}
