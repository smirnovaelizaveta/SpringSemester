import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable, BehaviorSubject} from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Task } from '../model/task';
import { Solution } from '../model/solution';
import { Project } from '../model/project';
import { MOCK_PROJECT } from '../mock/project';
import { MOCK_SOLUTION } from '../mock/solution';
import { SolutionService } from './solution.service'

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
    this.http.get<Task[]>(this.taskUrl)
      .subscribe(
        tasks => this.tasks.next(tasks)
      )

    this.solutionService.listenSolutionUpdates()
      .subscribe(update => this.updateSolutionInTask(update.taskId, update as Solution))
  }

  getTasks(): Observable<Task[]> {
    return this.tasks.asObservable();
  }

  downloadCode(taskId: number): Observable<any> {
    return this.http.get(this.taskUrl+`${taskId}/zip`,  { responseType: 'blob' });
  }

  getProjectTree(taskId: number): Observable<Project> {
    return this.http.get<Project>(this.taskUrl+`${taskId}/project-tree`)
  }

  uploadCode(taskId: number, formData: FormData): Observable<any> {
    return this.http.post(this.taskUrl+`${taskId}/solution/zip`, formData)
  }

  uploadTask(formData: FormData): Observable<any> {
    return this.http.post(this.taskUrl, formData)
    .pipe(
      tap(() => this.loadTasks())
    )
  }

  check(taskId: number, project: Project): Observable<Solution> {
    return this.http.post<Solution>(this.taskUrl+`${taskId}/solution`, project)
      .pipe(
        tap(solution => this.updateSolutionInTask(taskId, solution)),
        map(result => result as Solution)
      )
  }

  private updateSolutionInTask(taskId: number, solution: Solution) {
    this.tasks.next(
       this.tasks.getValue().map(task => {
         if(task.id === taskId) {
           task.solution = solution
         }
         return task;
       })
     )
  }
}
