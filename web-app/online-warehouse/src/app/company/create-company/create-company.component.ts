import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreateCompanyDto} from "../dto/create-company.dto";
import {CompanyService} from "../service/company.service";
import {CompanySizeEnum} from "../dto/enum/company-size.enum";
import {UsernameValidator} from "../../user/service/username.validator";
import {AuthorityDto} from "../../user/dto/authority.dto";
import {AuthorityNameEnum} from "../../user/dto/enum/authority-name.enum";
import {CreateUserDto} from "../../user/dto/create-user.dto";

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent implements OnInit {

  createCompanyForm: FormGroup;

  readonly today: Date = new Date();

  constructor(private fb: FormBuilder,
              private companyService: CompanyService,
              public usernameValidator: UsernameValidator) {
    this.createCompanyForm = fb.group({
      "name": ['', Validators.required],
      "sizeType": ['', Validators.required],
      "createUserDto": fb.group({
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
        // "authorities": [['ROLE_COMPANY_ADMIN'], [Validators.required]]
      })
    });
  }

  getUsernameErrors() {
    let createUserDtoGroup: FormGroup = (this.createCompanyForm.controls['createUserDto'] as FormGroup);
    return createUserDtoGroup.controls['username'].hasError('usernameInUse') ? 'Username is used' :
      createUserDtoGroup.controls['username'].hasError('required') ? 'Username is required' :
        createUserDtoGroup.controls['username'].hasError('maxlength') ? 'Username must be shorter than 50 characters' : '';
  }

  getPasswordErrors() {
    let createUserDtoGroup: FormGroup = (this.createCompanyForm.controls['createUserDto'] as FormGroup);
    return createUserDtoGroup.controls['password'].hasError('required') ? 'Password is required' :
      createUserDtoGroup.controls['password'].hasError('minlength') ? 'Password must be longer than 3 characters' : '';
  }

  getEmailErrors() {
    let createUserDtoGroup: FormGroup = (this.createCompanyForm.controls['createUserDto'] as FormGroup);
    return createUserDtoGroup.controls['email'].hasError('required') ? 'Email is required' :
      createUserDtoGroup.controls['email'].hasError('email') ? 'Please enter a valid email address' : '';
  }

  ngOnInit() {
  }

  getSizeTypes(): Array<string> {
    return Object.keys(CompanySizeEnum);
  }

  onSubmit(createCompanyForm: FormGroup): void {
    let value = createCompanyForm.value;
    console.log(value);
    let createCompanyDto: CreateCompanyDto = new CreateCompanyDto(null, null, new CreateUserDto());
    Object.assign(createCompanyDto, value);
    console.log(createCompanyDto);
    createCompanyDto.createUserDto.authorities = [];
    createCompanyDto.createUserDto.authorities.push(new AuthorityDto(AuthorityNameEnum.ROLE_COMPANY_ADMIN));
    this.companyService.saveCompany(createCompanyDto).subscribe((long: number) => {
      console.log(long)
    });
  }
}
