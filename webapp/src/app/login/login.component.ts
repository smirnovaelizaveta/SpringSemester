import { Input, Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
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

  form: FormGroup = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
  });

  error: string | undefined;

  submit() {
    if (this.form.valid) {
      this.userService.login(this.form.value.username, this.form.value.password)
        .subscribe(
          isValid => {
            console.log(isValid);
              if (isValid) {
                  sessionStorage.setItem('token', btoa(this.form.value.username + ':' + this.form.value.password));
              this.router.navigate(["/tasks"]);
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
}
