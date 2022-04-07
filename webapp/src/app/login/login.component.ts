import { Input, Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  username: string = ''
  password: string = ''

  error: string | undefined;

  submit() {
    this.userService.login(this.username, this.password)
      .subscribe(
        isValid => {
          if (isValid) {
            this.router.navigate(["/"]);
          } else {
            this.error = "Wrong password.";
          }
        },
        error => {
          if (error.status === 401) {
            this.error = "User not found."
          }
        }
      )
  }
}
