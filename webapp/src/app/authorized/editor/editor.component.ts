import { Component, OnInit, AfterViewInit, ViewChild, Input } from '@angular/core';
import { TasksService } from '../service/tasks.service';
import { SolutionService } from '../service/solution.service';
import { Status, Task } from '../model/task';
import { Solution } from '../model/solution'
import { Project, ProjectFile } from '../model/project';
import { NestedTreeControl } from '@angular/cdk/tree';
import { MatTreeNestedDataSource } from '@angular/material/tree';
import { Router, ActivatedRoute, Params } from '@angular/router';
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
    private solutionService: SolutionService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.tasks = this.tasksService.getTasks();
    this.tasks.subscribe(tasks => {
      if(tasks.length > 0) {
        this.route.params.subscribe(params  => {
          const taskId: number = +params['taskId'];
          this.selectedTask = tasks.find((task: Task) => task.id === taskId);
          if(!this.selectedTask) {
            window.alert(`Task with id ${taskId} does not exist.`)
            this.router.navigate(['/tasks'])
          } else {
            console.log(this.selectedTask)
            this.tasksService.getProjectTree(this.selectedTask!.id)
            .subscribe(project => {
                if(project) {
                  const data = project.files;
                  this.dataSource.data = data;
                  this.treeControl.dataNodes = data;
                  this.treeControl.expandAll();
                  console.log(this.treeControl)
                }
              }
            )
          }
        })
       }
    })
  }


    // this.tasksService.


  @ViewChild('tree') tree: any;

  selectFile(file: ProjectFile) {
    console.log(file);
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
      this.tasksService.uploadCode(this.selectedTask!.id, formData)
        .subscribe(() => this.selectedTask!.solution = {correct: null});
    }
  }

  displayName(projectFile: ProjectFile) {
    let displayName: string = projectFile.name;
    if(displayName.endsWith('/')) {
      displayName=displayName.slice(0,-1);
    }

    return displayName.substring(displayName.lastIndexOf('/') + 1)
  }
}
