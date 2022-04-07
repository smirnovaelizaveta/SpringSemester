import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TasksService } from '../service/tasks.service'
import { Task, Status } from '../model/task'
import { Observable } from 'rxjs';


@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(
    private tasksService: TasksService,
    private route: ActivatedRoute
  ) {}

  tasks?: Observable<Task[]>
  selectedTaskId: number | undefined;

  Status: any = Status;

  ngOnInit() {
    this.tasks = this.tasksService.getTasks();

    this.route.params
      .subscribe(params => {this.selectedTaskId = +params['taskId']; console.log(params)});
  }

}
