import { Component, OnInit  } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from './service/user.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent /*implements OnInit*/ {
  // constructor(
    // private userService: UserService,
    // private router: Router
  // ) {}

  // ngOnInit() {
  //   this.userService.getCurrentUser()
  //   .subscribe(
  //     result => {
  //       if (/*condition*/true) {
  //         this.router.navigate(['/tasks']);
  //       } else {
  //         this.redirectToTasks();
  //       }
  //     }
  //   )
  // }
}
