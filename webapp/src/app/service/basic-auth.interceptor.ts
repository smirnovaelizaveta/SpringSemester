import { Injectable } from '@angular/core';
import { Router } from "@angular/router"

import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

// import { AuthenticationService } from '@app/_services';

@Injectable()
export class BasicAuthInterceptor implements HttpInterceptor {
    constructor(private router: Router) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        console.log("intercepted request", request)
        request = request.clone({
             setHeaders: { 
                 "X-Requested-With": "XMLHttpRequest"
             }
         });

        const token: string | null = sessionStorage.getItem("token");
        if (token) {
            request = request.clone({
                setHeaders: { 
                    Authorization: `Basic ${token}`
                }
            });
        }

        return next.handle(request);
    }
}