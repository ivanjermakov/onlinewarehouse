import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "./auth/_services";
import {CompanyService} from "./company/service/company.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'online-warehouse-test';
  user = localStorage.getItem('currentUser');
  private companyName: string;
  private companyLogo: string;

  constructor(private auth: AuthenticationService,
              private companyService: CompanyService) {
  }

  ngOnInit(): void {
    if (!this.auth.getCompanyLogo()) {
      this.companyService.getCompanyLogo(this.auth.getCompanyId()).subscribe(logo => {
        this.companyLogo = logo;
      });
    } else {
      this.companyLogo = this.auth.getCompanyLogo();
    }

    this.companyName = this.auth.getCompanyName();
  }
}
