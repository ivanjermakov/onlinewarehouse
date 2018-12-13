import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {AuthenticationService} from '../_services';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {API_BASE_URL} from "../../base-server-url";
import {HttpClient} from "@angular/common/http";
import {UserDto} from "../../user/dto/user.dto";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";
import {User} from "../_models";
import {UserService} from "../../user/service/user.service";
import {UserActivationDto} from "../../user/dto/user-activation.dto";

@Component({
  selector: 'app-activation',
  templateUrl: 'activation.component.html',
  styleUrls: ['./login.component.css']
})

export class ActivationComponent implements OnInit {

  private pageLoading = true;
  private loading = false;
  private returnUrl: string;

  private credError: boolean = false;
  private connectionError: boolean = false;

  private resetForm: FormGroup;
  private user: UserActivationDto;

  constructor(
    private http: HttpClient,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private errorToast: RequestErrorToastHandlerService,
    private fb: FormBuilder) {
    this.resetForm = fb.group({
      password: ['', Validators.required],
      confirmPassword: ['', [Validators.required]]
    })
  }

  ngOnInit() {
    this.authenticationService.logout();
    this.resetForm.valueChanges.subscribe(() => {
      this.credError = false;
      this.connectionError = false;
    });
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';

    const code = this.route.snapshot.paramMap.get('code');
    const path: string = API_BASE_URL + '/activate/' + code;

    this.http.get<UserActivationDto>(path).subscribe((user) => {
      this.user = user;

      if(user) this.pageLoading = false;
      if(!user){
        this.router.navigateByUrl('/login');
        this.errorToast.handleCustomError('got it?????', 'BAAAAD!!!');
      }
    });
  }

  // getPasswordErrors() {
  //   const password = this.resetForm.controls['password'].value;
  //   const confirmPassword = this.resetForm.controls['confirmPassword'].value;
  //   if(password !== confirmPassword){
  //     return this.resetForm.controls['confirmPassword'].hasError();
  //   }
  // }

  reset() {
    this.loading = true;
    this.user.password = this.resetForm.controls['password'].value;
    this.userService.setPassword(this.user).subscribe();
    // this.authenticationService.login(this.resetForm.value.login, this.resetForm.value.password)
    //   .then(() => {
    //     this.router.navigate([this.returnUrl]);
    //   })
    //   .catch((error) => {
    //     if (error.status === 0) {
    //       this.connectionError = true;
    //     }
    //     if (error.status === 401) {
    //       this.credError = true;
    //     }
    //     this.loading = false;
    //   });
  }


}
