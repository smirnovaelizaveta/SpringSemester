import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(
      catchError((error) => { 
        console.log("Caught error: ", error)
        if (error instanceof HttpErrorResponse && error.status) {
          switch (error.status) {
            case 401:      //login
              this.router.navigate(["/login"]);
              break;
            case 403:     //forbidden
              this.router.navigate(["/login"]);
              break;
          }
        }
        return throwError(error);
      })
    )
  }
}