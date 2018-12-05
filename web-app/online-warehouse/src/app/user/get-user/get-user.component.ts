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

  private error: any;

  constructor(private userService: UserService,
              private route: ActivatedRoute,
              private fb: FormBuilder,
              private auth: AuthenticationService,
              private router: Router) {
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
            this.loadingSubject.next(false);
          }, (err: any) => {
            this.error = err;
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
      .subscribe((userId: number) => {
          this.getUser();
          console.log(userId);
          this.loadingSubject.next(false);
        }, (err: any) => {
          this.error = err;
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
      .subscribe((userId: number) => {
          this.getUser();
          console.log(userId);
          this.loadingSubject.next(false);
        }, (err: any) => {
          this.error = err;
        }
      );
  }

  resetPassword() {
    this.userService.resetPassword(this.user.id)
      .subscribe((userId: number) => {
          this.getUser();
          console.log(userId);
          this.loadingSubject.next(false);
        }, (err: any) => {
          this.error = err;
        }
      );
  }

  deleteUser() {
    this.userService.deleteUser(this.user.id)
      .subscribe((userId: number) => {
          this.getUser();
          this.router.navigate([`app/list-users`]);
          console.log(userId);
          this.loadingSubject.next(false);
        }, (err: any) => {
          this.error = err;
        }
      );
  }
}
