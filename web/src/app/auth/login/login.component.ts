import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;

  constructor(private authService: AuthService, private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    this.form = this.fb.group({
      identifiant: [null, Validators.required],
      password: [null, Validators.required]
    })
  }


  getFormtoModel() {
    return {
      username: this.form.get('identifiant').value,
      password: this.form.get('password').value,
    };
  }


  login() {
    if(this.form.valid) {
      this.authService.login(this.getFormtoModel()).subscribe(res => {
        this.router.navigateByUrl('/calendar');
      });
    }
  }
}
