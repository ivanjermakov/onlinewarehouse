import {Component} from '@angular/core';
import {AuthenticationService} from "./auth/_services";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'online-warehouse-test';
  currentComponentName: string;
  user = localStorage.getItem('currentUser');
  private companyName: string;

  constructor(private auth: AuthenticationService) {
    this.companyName = auth.getCompanyName();
  }
}
