import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable, BehaviorSubject, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient
  ) { }

  getCurrentUser(): Observable<any> {
    return this.http.get("api/user");
  }

  login(username: string, password: string): Observable<any> {
    return this.http.post("api/login", {username: username, password: password})
      .pipe(
         tap( isValid => {
           if(isValid) {
              sessionStorage.setItem('token', btoa(username + ':' + password));
              sessionStorage.setItem('username', username);
           }
         })
      );
  }

  register(username: string, password: string): Observable<any> {
    return this.http.post("api/user", {username: username, password: password})
      .pipe(
         tap(() => this.saveCredentials(username, password))
      );
  }

  logout() {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('username');
  }

  saveCredentials(username: string, password: string) {
    sessionStorage.setItem('token', btoa(username + ':' + password));
    sessionStorage.setItem('username', username);
  }
}
