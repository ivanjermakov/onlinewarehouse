import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {AuthenticationService} from '../_services';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  templateUrl: 'login.component.html'
})

export class LoginComponent implements OnInit {

  loading = false;
  returnUrl: string;

  private credError: boolean = false;
  private connectionError: boolean = false;

  private loginForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private fb: FormBuilder) {
    this.loginForm = fb.group({
      login: ['', Validators.required],
      password: ['', [Validators.required]]
    })
  }

  ngOnInit() {
    // reset login status
    this.authenticationService.logout();

    this.loginForm.valueChanges.subscribe(() => {
      this.credError = false;
      this.connectionError = false;
    });

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  login() {
    // this.reqError = {};
    this.loading = true;
    this.authenticationService.login(this.loginForm.value.login, this.loginForm.value.password)
      .then(() => {
        this.router.navigate([this.returnUrl]);
      })
      .catch((error) => {
        if (error.status === 0) {
          this.connectionError = true;
        }
        if (error.status === 401) {
          this.credError = true;
        }
        this.loading = false;
      });
  }

}
