import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'online-warehouse-test';
  currentComponentName: string;
  user = localStorage.getItem('currentUser');

  menu: boolean = true;

  changeCurrentComponentName(e) {
    this.currentComponentName = e;
  }

}
