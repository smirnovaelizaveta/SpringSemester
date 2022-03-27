import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
// import { ResponseContentType } from '@angular/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { Task, Status } from '../model/task';
import { MOCK_PROJECT } from '../mock/project';
// import { MessageService } from './message.service';


interface TaskDto {
  id: number,
  name: string,
  description: string,
  difficultylevel: number
}

@Injectable({ providedIn: 'root' })
export class TasksService {

  private taskUrl = 'api/task/'; 

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
  ) { }

  /** GET heroes from the server */
  getTasks(): Observable<Task[]> {
    return this.http.get<TaskDto[]>(this.taskUrl)
      .pipe(
        // tap(_ => this.log('fetched heroes')),
        map((taskDtos) => taskDtos.map((dto) => Object(
          {
            id: dto.id,
            name: dto.name,
            description: dto.description,
            difficultyLevel: dto.difficultylevel,
            project: MOCK_PROJECT
          }) as Task))
        // catchError(this.handleError<Task[]>('getTasks', []))
      );
  }

  downloadCode(taskId: number): Observable<any> {
    return this.http.get(this.taskUrl+`${taskId}/zip`,  { responseType: 'blob' });
  }

  uploadCode(formData: FormData): Observable<any> {
    return this.http.post('/api/solution', formData)
  }

  uploadTask(formData: FormData): Observable<any> {
    return this.http.post(this.taskUrl, formData)
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
