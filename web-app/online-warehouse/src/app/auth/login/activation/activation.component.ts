import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';

import {AuthenticationService} from '../../_services/index';
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {API_BASE_URL} from "../../../base-server-url";
import {HttpClient} from "@angular/common/http";
import {RequestErrorToastHandlerService} from "../../../shared/toast/request-error-handler/request-error-toast-handler.service";
import {UserService} from "../../../user/service/user.service";
import {UserActivationDto} from "../../../user/dto/user-activation.dto";

@Component({
  selector: 'app-activation',
  templateUrl: 'activation.component.html',
  styleUrls: ['../login.component.css']
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
    }, {
      validator: ActivationComponent.MatchPassword
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

    this.http.get<UserActivationDto>(path)
      .subscribe((user: UserActivationDto) => {
          this.user = user;
          this.pageLoading = false;
        }, (err: any) => {
          this.errorToast.handleError(err);
          this.router.navigateByUrl('/login');
        }
      );
  }

  reset() {
    this.loading = true;
    this.user.password = this.resetForm.controls['password'].value;
    this.userService.setPassword(this.user).subscribe(() => {
      this.loading = false;
      this.authenticationService.login(this.user.username, this.user.password)
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
    });
  }

  static MatchPassword(control: AbstractControl) {
    let password = control.get('password').value; // to get value in input tag
    let confirmPassword = control.get('confirmPassword').value; // to get value in input tag
    if(password != confirmPassword) {
      control.get('confirmPassword').setErrors( {MatchPassword: true} )
    } else {
      return null
    }
  }
}
