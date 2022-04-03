import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

interface User {

}


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  getCurrentUser(): Observable<User> {
    return this.http.get("user");
  }

  login(login: string, password: string): Observable<any> {
    return this.http.post("login", {username: login, password: password});
  }

  logout(): Observable<any> {
    return this.http.post("sample url", null);

  }
}
