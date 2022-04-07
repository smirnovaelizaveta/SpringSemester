import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable, BehaviorSubject} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Task, Status } from '../model/task';
import { Project } from '../model/project';
import { MOCK_PROJECT } from '../mock/project';


interface TaskDto {
  id: number,
  name: string,
  description: string,
  difficultylevel: number
}

@Injectable({ providedIn: 'root' })
export class TasksService {
  constructor(
    private http: HttpClient,
    private route: ActivatedRoute
  ) {
    this.tasks = new BehaviorSubject<Task[]>(new Array<Task>());

    this.loadTasks();
  }

  taskUrl: string = 'api/task/'; 

  private tasks: BehaviorSubject<Task[]>;

  loadTasks() {
    this.http.get<TaskDto[]>(this.taskUrl)
      .pipe(
        map((taskDtos) => taskDtos.map((dto) => Object(
          {
            id: dto.id,
            name: dto.name,
            description: dto.description,
            difficultyLevel: dto.difficultylevel,
            project: MOCK_PROJECT
          }) as Task)),
      )
      .subscribe(
        tasks => this.tasks.next(tasks)
      )
  }

  getTasks(): Observable<Task[]> {
    return this.tasks.asObservable();
  }

  downloadCode(taskId: number): Observable<any> {
    return this.http.get(this.taskUrl+`${taskId}/zip`,  { responseType: 'blob' });
  }

  getProjectTree(taskId: number): Observable<Project> {
    return this.http.get(this.taskUrl+`${taskId}/project-tree`)
      .pipe(
        map((result) => MOCK_PROJECT as Project)
      );
  }

  uploadCode(formData: FormData): Observable<any> {
    return this.http.post('/api/solution', formData)
  }

  uploadTask(formData: FormData): Observable<any> {
    return this.http.post(this.taskUrl, formData)
    .pipe(
      tap(() => this.loadTasks())
    )
  }
}
