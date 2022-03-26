import { Component, OnInit, AfterViewInit, ViewChild } from '@angular/core';
import { TasksService } from '../service/tasks.service';
import { Status, Task } from '../model/task';
import { Project, ProjectFile } from '../model/project';
import { ActivatedRoute } from '@angular/router';
import { NestedTreeControl } from '@angular/cdk/tree';
import { MatTreeNestedDataSource } from '@angular/material/tree';

@Component({
  selector: 'app-editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit,  AfterViewInit  {

  public Status: any = Status;

  tasks?: Task[];
  selectedTask:  Task | undefined
  selectedFile: ProjectFile | undefined
  treeControl = new NestedTreeControl<ProjectFile>(node => node.children);
  dataSource = new MatTreeNestedDataSource<ProjectFile>();
  hasChild = (_: number, node: ProjectFile) => !!node.children && node.children.length > 0;


  constructor(
    private tasksService: TasksService,
    private route: ActivatedRoute
  ) { 
  }

  ngOnInit(): void {
    this.tasksService.getTasks()
    .subscribe(
      (tasks: Task[]) => {
        this.tasks = tasks;
        this.route.params.subscribe(routeParams => {
          const taskId = routeParams['taskId'];
          this.selectedTask = this.tasks!.find((task: Task) => task.id === taskId);
          const data = this.selectedTask!.project.files;
          this.dataSource.data = data;
          this.treeControl.dataNodes = data;
          this.treeControl.expandAll();
        });
      }
    );

    // this.tasksService.
  }

  @ViewChild('tree') tree: any;

  ngAfterViewInit() {
  }

  selectFile(file: ProjectFile) {
    this.selectedFile = file;
  }

  download(task: Task, format: String = "zip"): void {
    this.tasksService.downloadCode(task.id)
      .subscribe((response: any) =>{
            let dataType = response.type;
            let binaryData = [];
            binaryData.push(response);
            let downloadLink = document.createElement('a');
            downloadLink.href = window.URL.createObjectURL(new Blob(binaryData, {type: dataType}));
            downloadLink.setAttribute('download', `task${task.id}.${format}`);
            document.body.appendChild(downloadLink);
            downloadLink.click();
        });
  }

  upload(event: any) {
      const file:File = event.target.files[0];

      if (file) {
          const formData = new FormData();
          formData.append("file", file);
          this.tasksService.uploadCode(formData).subscribe();
      }
  }
}
