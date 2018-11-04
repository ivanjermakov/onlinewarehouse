import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup} from "@angular/forms";
import {CreateCompanyDto} from "../dto/create-company.dto";
import {CompanyService} from "../service/company.service";
import {CompanySizeEnum} from "../dto/enum/company-size.enum";

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent implements OnInit {

  createCompanyForm: FormGroup;
  sizeTypes = CompanySizeEnum;

  constructor(private fb: FormBuilder,
              private companyService: CompanyService) {
    this.createCompanyForm = fb.group({
      "name": [''],
      "sizeType": ['']
    });
  }

  ngOnInit() {
  }

  getSizeTypes(): Array<string> {
    return Object.keys(this.sizeTypes);
  }

  onSubmit(createCompanyForm: FormGroup): void {
    let value = createCompanyForm.value;
    let createCompanyDto: CreateCompanyDto = new CreateCompanyDto(null, null);
    Object.assign(createCompanyDto, value);
    this.companyService.saveCompany(createCompanyDto).subscribe((long: number) => {
      console.log(long)
    });
  }

}
