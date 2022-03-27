import { Component, OnInit } from '@angular/core';
import { TasksService } from '../service/tasks.service';
import { Status, Task } from '../model/task';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {
  constructor(private tasksService: TasksService) { }

  public Status: any = Status;
  tasks?: Task[];
  name: string = "";
  description: string = "";
  difficultyLevel: number = 1;

  ngOnInit(): void {
    this.getTasks()
  }

  getTasks(): void {
    this.tasksService.getTasks()
    .subscribe(tasks => this.tasks = tasks);
  }

  upload(event: any) {
      const file:File = event.target.files[0];

      if (file) {
          const formData = new FormData();
          formData.append("file", file);
          formData.append("name", this.name);
          formData.append("description", this.description);
          formData.append("difficultylevel", ""+this.difficultyLevel);
          console.log(formData);
          console.log("file", file);
          console.log("name", this.name);
          console.log("description", this.description);
          console.log("difficultylevel", ""+this.difficultyLevel);
          this.tasksService.uploadTask(formData).subscribe(
            () => this.getTasks()
          );
      }
  }
}
