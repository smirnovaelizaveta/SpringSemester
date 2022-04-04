import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service'

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  constructor(
    private userService: UserService,
    private router: Router
  ) { }

  ngOnInit() {
  }

  username: string = ''
  password: string = ''
  confirmPassword: string = ''

  error: string | undefined;

  submit() {
    console.log(this.username,this.password,this.confirmPassword);
    if(this.password === this.confirmPassword) {
      this.error = undefined;
      this.userService.register(this.username, this.password)
        .subscribe(
          () => this.router.navigate(["/tasks"])
        )
    } else {
      this.error = 'Entered passwords do not match!'
    }
  }
}
