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

  ngOnInit(): void {
    this.getTasks()
  }
  getTasks(): void {
    this.tasksService.getTasks()
    .subscribe(tasks => this.tasks = tasks);
  }

}
