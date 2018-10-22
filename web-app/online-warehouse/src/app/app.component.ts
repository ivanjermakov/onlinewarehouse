import {Component} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'online-warehouse-test';
  currentComponentName: string;

  menu = {
    'roles': [
      {
        'name': 'admin',
        'actions': [
          {
            'name': 'Home',
            'url': ''
          },
          {
            'name': 'viewUsers',
            'url': '/view-users'
          },
          {
            'name': 'createUser',
            'url': '/user'
          }
        ]
      },
      {
        'name': 'user',
        'actions': [
          {
            'name': 'Home',
            'url': ''
          },
          {
            'name': 'viewUsers',
            'url': '/view-users'
          }
          // ,
          // {
          //   'name': 'name3',
          //   'url': 'componentName3'
          // }
        ]
      }
    ]
  };

  changeCurrentComponentName(e) {
    this.currentComponentName = e;
  }

}
