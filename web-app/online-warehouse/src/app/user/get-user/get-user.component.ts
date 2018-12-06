import {Component, OnInit} from '@angular/core';
import {UserDto} from "../dto/user.dto";
import {UserService} from "../service/user.service";
import {finalize} from "rxjs/operators";
import {BehaviorSubject} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthorityNameEnum} from "../dto/enum/authority-name.enum";
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {AuthorityDto} from "../dto/authority.dto";
import {AuthenticationService} from "../../auth/_services";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Component({
  selector: 'app-get-user',
  templateUrl: './get-user.component.html',
  styleUrls: ['./get-user.component.css']
})
export class GetUserComponent implements OnInit {

  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private user: UserDto;

  private userJWTId: number;

  private authorityNameEnum = AuthorityNameEnum;

  private showChangeAuthorities: boolean = false;
  private authoritiesForm: FormControl;


  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private fb: FormBuilder,
              private auth: AuthenticationService,
              private router: Router,
              private errorToast: RequestErrorToastHandlerService) {
    this.userJWTId = auth.getUserId()
  }

  ngOnInit() {
    this.getUser();
  }

  getUser() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!Number.isNaN(id) && id != 0) {
      this.loadingSubject.next(true);
      this.userService.getUserById(id)
        .pipe(
          finalize(() => this.loadingSubject.next(false))
        )
        .subscribe((userDto: UserDto) => {
            this.user = userDto;
            this.buildAuthorityForm();
          }, (err: any) => {
            this.errorToast.handleError(err);
          }
        );
    }
  }

  buildAuthorityForm() {
    let authorityNameEnums = this.user.authorities.map(authority => authority.name);
    this.authoritiesForm = this.fb.control(authorityNameEnums, Validators.required);
  }

  submitAuthorityForm() {
    let authorityDtoList = this.authoritiesForm.value.map(authority => {
      return new AuthorityDto(authority)
    });
    this.showChangeAuthorities = false;
    this.userService.changeAuthorities(this.user.id, authorityDtoList)
      .pipe(finalize(() => this.loadingSubject.next(false)))
      .subscribe((userId: number) => {
          this.errorToast.handleSuccess('Authorities changed successfully', 'Changed successfully');
          this.getUser();
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }

  getAuthorities(): string[] {
    let strings = Object.keys(this.authorityNameEnum);
    for (let i = 0; i < strings.length;) {
      if (strings[i] === 'ROLE_ADMIN') {
        strings.splice(i, 1);
      } else {
        i++
      }
    }
    return strings;
  }

  changeEnableValue() {
    this.userService.changeEnableValue(this.user.id)
      .pipe(finalize(() => this.loadingSubject.next(false)))
      .subscribe((userId: number) => {
          this.getUser();
          this.errorToast.handleSuccess('Enable value changed successfully', 'Changed successfully');
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }

  resetPassword() {
    this.userService.resetPassword(this.user.id)
      .pipe(finalize(() => this.loadingSubject.next(false)))
      .subscribe((userId: number) => {
          this.getUser();
          this.errorToast.handleSuccess('Password reseted successfully', 'Changed successfully');
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }

  deleteUser() {
    this.userService.deleteUser(this.user.id)
      .pipe(finalize(() => this.loadingSubject.next(false)))
      .subscribe((userId: number) => {
          this.router.navigate([`app/list-users`]);
          this.errorToast.handleSuccess('User deleted successfully', 'Deleted successfully');
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }
}
