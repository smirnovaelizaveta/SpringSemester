import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ActivatedRoute} from '@angular/router';
import {BehaviorSubject, Observable} from 'rxjs';
import {map, tap} from 'rxjs/operators';
import {Status, Task} from '../model/task';
import {Solution} from '../model/solution';
import {Project} from '../model/project';
import {SolutionService} from './solution.service'


interface TaskDto {
  id: number,
  name: string,
  description: string,
  difficultylevel: number,
  solution: Solution
}

@Injectable({ providedIn: 'root' })
export class TasksService {
  constructor(
    private http: HttpClient,
    private route: ActivatedRoute,
    private solutionService: SolutionService
  ) {
    this.loadTasks();
  }

  taskUrl: string = 'api/task/';

  private tasks = new BehaviorSubject<Task[]>([]);

  loadTasks() {
    this.http.get<TaskDto[]>(this.taskUrl)
      .pipe(
        map((taskDtos) => taskDtos.map((dto) => Object(
          {
            id: dto.id,
            name: dto.name,
            description: dto.description,
            difficultyLevel: dto.difficultylevel,
            solution: dto.solution,
            status: dto.solution ? (dto.solution.correct? Status.SOLVED : Status.IN_PROGRESS) : Status.NOT_STARTED
          }) as Task)),
      )
      .subscribe(
        tasks => this.tasks.next(tasks)
      )

    this.solutionService.listenSolutionUpdates().subscribe(
       update => this.tasks.next(
         this.tasks.getValue().map(task => {
           if(task.id === update.taskId) {
             task.solution = update as Solution
           }
           return task;
         })
       )
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
        map((result) => result as Project)
      );
  }

  uploadCode(taskId: number, formData: FormData): Observable<any> {
    return this.http.post(this.taskUrl+`${taskId}/solution`, formData)
  }

  uploadTask(formData: FormData): Observable<any> {
    return this.http.post(this.taskUrl, formData)
    .pipe(
      tap(() => this.loadTasks())
    )
  }
}
