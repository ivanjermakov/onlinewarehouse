import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreateCompanyDto} from "../dto/create-company.dto";
import {CompanyService} from "../service/company.service";
import {CompanySizeEnum} from "../dto/enum/company-size.enum";
import {UsernameValidator} from "../../user/service/username.validator";
import {AuthorityDto} from "../../user/dto/authority.dto";
import {AuthorityNameEnum} from "../../user/dto/enum/authority-name.enum";
import {CreateUserDto} from "../../user/dto/create-user.dto";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";
import {Observable} from "rxjs";
import {map, startWith} from "rxjs/operators";
import {AddressService} from "../../shared/address/service/address.service";

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent implements OnInit {

  createCompanyForm: FormGroup;
  readonly today: Date = new Date();
  public countryOptions: any[];
  countryFilteredOptions: Observable<any[]>;
  public regionOptions: any[];
  regionFilteredOptions: Observable<any[]>;
  private imageSrc: string = '';

  constructor(private fb: FormBuilder,
              private companyService: CompanyService,
              public usernameValidator: UsernameValidator,
              private addressService: AddressService,
              private errorToast: RequestErrorToastHandlerService) {
    this.createCompanyForm = fb.group({
      "name": ['', [Validators.required, Validators.maxLength(40)]],
      "sizeType": ['', Validators.required],
      "createUserDto": fb.group({
        "username": ['',
          Validators.compose(
            [Validators.maxLength(50),
              Validators.required, Validators.minLength(3)]),
          usernameValidator.checkUsername.bind(usernameValidator)],
        // "password": ['', [Validators.required, Validators.minLength(3)]],
        "firstname": ['', [Validators.required, Validators.maxLength(40)]],
        "lastname": ['', [Validators.required, Validators.maxLength(40)]],
        "patronymic": [''],
        "email": ['', [Validators.required, Validators.email, Validators.maxLength(50)]],
        "address": fb.group({
          country: ['', [Validators.required, Validators.maxLength(40)]],
          region: ['', [Validators.required, Validators.maxLength(55)]],
          locality: ['', [Validators.required, Validators.maxLength(50)]],
        }),
        "birth": ['', [Validators.required]],
      }),
      "logo": ['']
    });
  }

  getUsernameErrors() {
    let createUserDtoGroup: FormGroup = (this.createCompanyForm.controls['createUserDto'] as FormGroup);
    return createUserDtoGroup.controls['username'].hasError('usernameInUse') ? 'Username is used' :
      createUserDtoGroup.controls['username'].hasError('required') ? 'Username is required' :
        createUserDtoGroup.controls['username'].hasError('maxlength') ? 'Username must be shorter than 50 characters' :
          createUserDtoGroup.controls['username'].hasError('minlength') ? 'Username must be longer than 3 characters' : '';
  }

  // getPasswordErrors() {
  //   let createUserDtoGroup: FormGroup = (this.createCompanyForm.controls['createUserDto'] as FormGroup);
  //   return createUserDtoGroup.controls['password'].hasError('required') ? 'Password is required' :
  //     createUserDtoGroup.controls['password'].hasError('minlength') ? 'Password must be longer than 3 characters' : '';
  // }

  getEmailErrors() {
    let createUserDtoGroup: FormGroup = (this.createCompanyForm.controls['createUserDto'] as FormGroup);
    return createUserDtoGroup.controls['email'].hasError('required') ? 'Email is required' :
      createUserDtoGroup.controls['email'].hasError('email') ? 'Please enter a valid email address' :
        createUserDtoGroup.controls['email'].hasError('maxlength') ? 'Email must be shorter than 50 characters' : '';
  }

  ngOnInit() {
    this.addressService.getCountries().subscribe((result) => {
      this.countryOptions = result;
      this.countryFilteredOptions = ((this.createCompanyForm.controls['createUserDto'] as FormGroup)
        .controls['address'] as FormGroup).controls['country'].valueChanges
        .pipe(
          startWith<string | Object>(''),
          map((value: any) => typeof value === 'string' || value === null ? value : value.title),
          map(title => title ? this._countryFilter(title) : this.countryOptions.slice())
        )
      ;
    });
  }

  regionDisplayFn(region?: any): string | undefined {
    return region ? region : undefined;
  }

  getRegions(event) {
    let id = event.option.value.id;
    ((this.createCompanyForm.controls['createUserDto'] as FormGroup)
      .controls['address'] as FormGroup).controls['country'].patchValue(event.option.value.title);
    if (id !== 0) {
      this.addressService.getRegions(id).subscribe((result) => {
        this.regionOptions = result;
        this.regionFilteredOptions = ((this.createCompanyForm.controls['createUserDto'] as FormGroup)
          .controls['address'] as FormGroup).controls['region'].valueChanges
          .pipe(
            startWith<string | Object>(''),
            map((value: any) => typeof value === 'string' || value === null ? value : value.title),
            map(title => title ? this._regionFilter(title) : this.regionOptions.slice())
          );
        ((this.createCompanyForm.controls['createUserDto'] as FormGroup)
          .controls['address'] as FormGroup).controls['country'].valueChanges
          .subscribe(() => this.regionOptions = [])
      });
    }
  }

  getSizeTypes(): Array<string> {
    return Object.keys(CompanySizeEnum);
  }

  onSubmit(createCompanyForm: FormGroup): void {
    let value = createCompanyForm.value;
    let createCompanyDto: CreateCompanyDto = new CreateCompanyDto(null, null, new CreateUserDto(), null);
    Object.assign(createCompanyDto, value);
    createCompanyDto.logo = this.imageSrc;
    createCompanyDto.createUserDto.authorities = [];
    createCompanyDto.createUserDto.authorities.push(new AuthorityDto(AuthorityNameEnum['Admin']));
    this.companyService.saveCompany(createCompanyDto)
      .subscribe(() => {
          this.errorToast.handleSuccess('Company saved successfully', 'Saved successfully');
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }

  handleInputChange(e) {
    let file = e.dataTransfer ? e.dataTransfer.files[0] : e.target.files[0];
    let pattern = /image-*/;
    let reader = new FileReader();
    if (!file.type.match(pattern)) {
      alert('invalid format');
      return;
    }
    reader.onload = this._handleReaderLoaded.bind(this);
    reader.readAsDataURL(file);
  }

  _handleReaderLoaded(e) {
    let reader = e.target;
    this.imageSrc = reader.result;
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
