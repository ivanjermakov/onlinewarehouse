import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../auth/_services";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  private admin = {authority: "ROLE_ADMIN"};
  private companyAdmin = {authority: "ROLE_COMPANY_ADMIN"};
  private warehouseOwner = {authority: "ROLE_WAREHOUSE_OWNER"};
  private dispatcher = {authority: "ROLE_DISPATCHER"};
  private manager = {authority: "ROLE_MANAGER"};
  private inspector = {authority: "ROLE_INSPECTOR"};

  private userAuthorities: Array<String> = new Array<String>();

  constructor(
    private auth: AuthenticationService
  ) {
  }

  ngOnInit() {
    this.auth.getAuthorities().forEach(auth =>
      this.userAuthorities.push(auth.authority)
    )
  }

}
