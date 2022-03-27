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

  ngOnInit(): void {
  }

  form: FormGroup = new FormGroup({
      username: new FormControl(''),
      password: new FormControl(''),
    });

    submit() {
      if (this.form.valid) {
        this.userService.login(this.form.value.username, this.form.value.password)
          .subscribe(() => {}, err => {this.router.navigate(["/tasks"])})
      }
    }
    @Input() error: string | undefined;
}
