import { Component, OnInit } from '@angular/core';
import { TasksService } from '../service/tasks.service';
import { Task } from '../model/task';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {
  constructor(private tasksService: TasksService) { }

  tasks?: Observable<Task[]>;
  name: string = "";
  description: string = "";
  difficultyLevel: number = 1;

  ngOnInit(): void {
    this.tasks = this.tasksService.getTasks();
  }

  upload(event: any) {
      const file:File = event.target.files[0];

      if (file) {
          const formData = new FormData();
          formData.append("file", file);
          formData.append("name", this.name);
          formData.append("description", this.description);
          formData.append("difficultylevel", ""+this.difficultyLevel);
          this.tasksService.uploadTask(formData).subscribe();
      }
  }
}
