import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  currentMenuName = null;
  isAuth = false;

  changeCurrentMenu(e) {
    this.currentMenuName = e;
  }
}
