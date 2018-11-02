import {Component, OnInit} from '@angular/core';
import {CompanyService} from "../service/company.service";
import {CompanyDto} from "../dto/company.dto";
import * as actionTypeEnum from "../dto/enum/action-type.enum";


@Component({
  selector: 'app-company-list',
  templateUrl: './company-list.component.html',
  styleUrls: ['./company-list.component.css']
})
export class CompanyListComponent implements OnInit {
  companyList: CompanyDto[];
  actionType = actionTypeEnum.ActionTypeEnum;

  constructor(private companyService: CompanyService) {
  }

  ngOnInit() {
    this.companyService.getAllCompanies().subscribe((companies: CompanyDto[]) => {
      this.companyList = companies;
    })
  }

  disableCompany(companyId: number) {
    console.log("lol");
    this.companyService.setCompanyDisabled(companyId);
  }

  enableCompany(companyId: number) {
    this.companyService.setCompanyEnabled(companyId);
  }
}
