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
              private companyService: CompanyService) {
    this.createCompanyForm = fb.group({
      "name": ['', Validators.required],
      "sizeType": ['', Validators.required]
    });
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
