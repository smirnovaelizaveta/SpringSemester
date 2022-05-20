import { Injectable, OnDestroy } from '@angular/core';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { Router } from '@angular/router';
// import { environment } from '../../environments/environment';
import { catchError, tap, switchAll } from 'rxjs/operators';
import { Observable, Subject } from 'rxjs';
import { SolutionUpdate } from '../model/solution'
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
// export const WS_ENDPOINT = environment.wsEndpoint;

@Injectable({
  providedIn: 'root'
})
export class SolutionService implements OnDestroy  {
  stompClient: any;
  webSocketEndPoint: string = 'http://localhost:7778/ws';
  topic: string = "/user/topic/solution";
  solutionUpdate: Subject<SolutionUpdate> = new Subject();

  constructor(private router: Router) {
    if(sessionStorage.getItem("token")){
      this.init();
    }
  }

  ngOnDestroy() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
  }

  private init() {
    const ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    // this.stompClient.debug = null;
    const _this = this;
    _this.stompClient.connect({}, (frame: any) => {
        _this.stompClient.subscribe(_this.topic, (message: any)  => {
            _this.solutionUpdate.next(JSON.parse(message.body))
        });
    });
  }

  public listenSolutionUpdates(): Observable<SolutionUpdate> {
    return this.solutionUpdate.asObservable();
  }
}
