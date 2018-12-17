import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UsernameValidator} from "../service/username.validator";
import {AuthorityNameEnum} from "../dto/enum/authority-name.enum";
import {UserService} from "../service/user.service";
import {CreateUserDto} from "../dto/create-user.dto";
import {AuthorityDto} from "../dto/authority.dto";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";
import {Observable} from "rxjs";
import {AddressService} from "../../shared/address/service/address.service";
import {map, startWith} from "rxjs/operators";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  createUserDto: FormGroup;
  private authorityNameEnum = AuthorityNameEnum;

  private today = new Date();

  public countryOptions: any[];
  countryFilteredOptions: Observable<any[]>;
  public regionOptions: any[];
  regionFilteredOptions: Observable<any[]>;

  constructor(private fb: FormBuilder,
              public usernameValidator: UsernameValidator,
              private userService: UserService,
              private addressService: AddressService,
              private errorToast: RequestErrorToastHandlerService) {
    this.createUserDto = fb.group({
      "username": ['',
        Validators.compose(
          [Validators.maxLength(50),
            Validators.required, Validators.minLength(3)]),
        usernameValidator.checkUsername.bind(usernameValidator)],
      "password": ['', [Validators.required, Validators.minLength(3)]],
      "firstname": ['', [Validators.required, Validators.maxLength(40)]],
      "lastname": ['', [Validators.required, Validators.maxLength(40)]],
      "patronymic": [''],
      "email": ['', [Validators.required, Validators.email, Validators.maxLength(50)]],
      "address": fb.group({
        country: ['', [Validators.required, Validators.maxLength(40)]],
        region: ['', [Validators.required, Validators.maxLength(55)]],
        locality: ['', [Validators.required, Validators.maxLength(50)]]
      }),
      "birth": ['', [Validators.required]],
      "authorities": ['', [Validators.required]]
    })
  }

  ngOnInit() {
    this.addressService.getCountries().subscribe((result) => {
      this.countryOptions = result;
      this.countryFilteredOptions = (this.createUserDto.controls['address'] as FormGroup).controls['country'].valueChanges
        .pipe(
          startWith<string | Object>(''),
          map((value: any) => typeof value === 'string' || value === null ? value : value.title),
          map(title => title ? this._countryFilter(title) : this.countryOptions.slice())
        )
      ;
    });
  }

  getAuthorities(): string[] {
    let strings = Object.keys(this.authorityNameEnum);
    for (let i = 0; i < strings.length;) {
      if (strings[i] === 'ROLE_COMPANY_ADMIN' || strings[i] === 'ROLE_ADMIN') {
        strings.splice(i, 1);
      } else {
        i++
      }
    }
    return strings;
  }

  getUsernameErrors() {
    return this.createUserDto.controls['username'].hasError('usernameInUse') ? 'Username is used' :
      this.createUserDto.controls['username'].hasError('required') ? 'Username is required' :
        this.createUserDto.controls['username'].hasError('maxlength') ? 'Username must be shorter than 50 characters' :
          this.createUserDto.controls['username'].hasError('minlength') ? 'Username must be longer than 3 characters' : '';
  }

  getPasswordErrors() {
    return this.createUserDto.controls['password'].hasError('required') ? 'Password is required' :
      this.createUserDto.controls['password'].hasError('minlength') ? 'Password must be longer than 3 characters' : '';
  }

  getEmailErrors() {
    return this.createUserDto.controls['email'].hasError('required') ? 'Email is required' :
      this.createUserDto.controls['email'].hasError('email') ? 'Please enter a valid email address' :
        this.createUserDto.controls['email'].hasError('maxlength') ? 'Email must be shorter than 50 characters' : '';
  }

  onSubmit() {

    let createUserDto: CreateUserDto = new CreateUserDto();
    Object.assign(createUserDto, this.createUserDto.value);
    createUserDto.authorities = this.createUserDto.value.authorities.map(authority => {
      return new AuthorityDto(authority)
    });
    this.userService.saveUser(createUserDto)
      .subscribe((id: number) => {
          this.errorToast.handleSuccess('New user saved successfully', 'Saved successfully');
          this.clearForm();
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );

  }

  clearForm() {
    this.createUserDto.reset()
  }

  regionDisplayFn(region?: any): string | undefined {
    return region ? region : undefined;
  }

  getRegions(event) {
    let id = event.option.value.id;
    (this.createUserDto.controls['address'] as FormGroup).controls['country'].patchValue(event.option.value.title);
    if (id !== 0) {
      this.addressService.getRegions(id).subscribe((result) => {
        this.regionOptions = result;
        this.regionFilteredOptions = (this.createUserDto.controls['address'] as FormGroup).controls['region'].valueChanges
          .pipe(
            startWith<string | Object>(''),
            map((value: any) => typeof value === 'string' || value === null ? value : value.title),
            map(title => title ? this._regionFilter(title) : this.regionOptions.slice())
          );
        (this.createUserDto.controls['address'] as FormGroup).controls['country'].valueChanges
          .subscribe(() => this.regionOptions = [])
      });
    }
  }


  private _countryFilter(title: string): Object[] {
    const filterValue = title.toLowerCase();

    return this.countryOptions.filter(option => option.title.toLowerCase().indexOf(filterValue) === 0);
  }

  private _regionFilter(title: string): Object[] {
    const filterValue = title.toLowerCase();

    return this.regionOptions.filter(option => option.title.toLowerCase().indexOf(filterValue) === 0);
  }
}
