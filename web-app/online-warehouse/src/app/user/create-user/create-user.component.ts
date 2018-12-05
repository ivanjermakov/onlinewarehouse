import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UsernameValidator} from "../service/username.validator";
import {AuthorityNameEnum} from "../dto/enum/authority-name.enum";
import {UserService} from "../service/user.service";
import {CreateUserDto} from "../dto/create-user.dto";
import {AuthorityDto} from "../dto/authority.dto";

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css']
})
export class CreateUserComponent implements OnInit {

  createUserDto: FormGroup;
  private authorityNameEnum = AuthorityNameEnum;

  private today = new Date();

  private error: any;

  constructor(private fb: FormBuilder,
              public usernameValidator: UsernameValidator,
              private userService: UserService) {
    this.createUserDto = fb.group({
      "username": ['',
        Validators.compose(
          [Validators.maxLength(50),
            Validators.required]),
        usernameValidator.checkUsername.bind(usernameValidator)],
      "password": ['', [Validators.required, Validators.minLength(3)]],
      "firstname": ['', [Validators.required]],
      "lastname": ['', [Validators.required]],
      "patronymic": [''],
      "email": ['', [Validators.required, Validators.email]],
      "address": fb.group({
        "country": ['', Validators.required],
        "region": ['', Validators.required],
        "locality": ['', Validators.required]
      }),
      "birth": ['', [Validators.required]],
      "authorities": ['', [Validators.required]]
    })
  }

  ngOnInit() {
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
        this.createUserDto.controls['username'].hasError('maxlength') ? 'Username must be shorter than 50 characters' : '';
  }

  getPasswordErrors() {
    return this.createUserDto.controls['password'].hasError('required') ? 'Password is required' :
      this.createUserDto.controls['password'].hasError('minlength') ? 'Password must be longer than 3 characters' : '';
  }

  getEmailErrors() {
    return this.createUserDto.controls['email'].hasError('required') ? 'Email is required' :
      this.createUserDto.controls['email'].hasError('email') ? 'Please enter a valid email address' : '';
  }

  onSubmit() {

    let createUserDto: CreateUserDto = new CreateUserDto();
    Object.assign(createUserDto, this.createUserDto.value);
    createUserDto.authorities = this.createUserDto.value.authorities.map(authority => {
      return new AuthorityDto(authority)
    });
    this.userService.saveUser(createUserDto)
      .subscribe((id: number) => {
          console.log(id);
          this.clearForm();
        }, (err: any) => {
          this.error = err;
          console.log(this.error);
        }
      );
  }

  clearForm() {
    this.createUserDto.reset()
  }
}
