import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
// import { ResponseContentType } from '@angular/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Task, Status } from '../task';
// import { MessageService } from './message.service';


interface TaskDto {
  taskId: string,
  taskName: string,
  taskDescription: string
}

@Injectable({ providedIn: 'root' })
export class TasksService {

  private tasksUrl = 'api/tasks'; 

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
  ) { }

  /** GET heroes from the server */
  getTasks(): Observable<Task[]> {
    return this.http.get<TaskDto[]>(this.tasksUrl)
      .pipe(
        // tap(_ => this.log('fetched heroes')),
        map((taskDtos) => taskDtos.map((dto) => Object({id: dto.taskId, name: dto.taskName, description: dto.taskDescription}) as Task))
        // catchError(this.handleError<Task[]>('getTasks', []))
      );
  }

  downloadCode(taskId: string): Observable<any> {
    return this.http.get(this.tasksUrl+`/${taskId}/download`,  { responseType: 'blob' });
  }

  uploadCode(formData: FormData): Observable<any> {
    return this.http.post('/api/uploadFile', formData)
  }
  /**
   * Handle Http operation that failed.
   * Let the app continue.
   *
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);
      return of(result as T);
    };
  }
}
