import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { Task, Status } from '../task';
import { TasksService } from './tasks.service'

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {

  public Status: any = Status;

  tasks?: Task[];

  constructor(private tasksService: TasksService) { }

  ngOnInit(): void {
    this.getTasks()
  }

  getTasks(): void {
    this.tasksService.getTasks()
    .subscribe(tasks => this.tasks = tasks);
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
