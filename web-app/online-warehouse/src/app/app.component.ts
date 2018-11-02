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
            'name': 'Посмотреть все компании',
            'url': '/list-companies'
          },
          {
            'name': 'Создать компанию',
            'url': '/create-company'
          },
          {
            'name': 'Test',
            'url': '/test'
          }
        ]
      },
      {
        'name': 'company admin',
        'actions': [
          {
            'name': 'Посмотреть пользователей',
            'url': '/view-users'
          },
          {
            'name': 'Создать пользователя',
            'url': '/user'
          }
        ]
      },
      {
        'name': 'warehouse owner',
        'actions': [
          {
            'name': 'Отчет прибыли',
            'url': ''
          },
          {
            'name': 'Отчет краж',
            'url': ''
          },
          {
            'name': 'Еще какой-то отчет',
            'url': ''
          }
        ]
      },
      {
        'name': 'dispatcher',
        'actions': [
          {
            'name': 'Зарегистрировать ТТН',
            'url': ''
          },
          // {
          //   'name': 'viewUsers',
          //   'url': '/view-users'
          // }
          // ,
          // {
          //   'name': 'name3',
          //   'url': 'componentName3'
          // }
        ]
      },
      {
        'name': 'manager',
        'actions': [
          {
            'name': 'Создать ТТН',
            'url': ''
          },
          {
            'name': 'Товарные партии к размещению',
            'url': ''
          }
          ,
          // {
          //   'name': 'name3',
          //   'url': 'componentName3'
          // }
        ]
      },
      {
        'name': 'inspector',
        'actions': [
          {
            'name': 'Зарегестрированные ТТН',
            'url': ''
          },
          {
            'name': 'Создать акт',
            'url': ''
          }
          ,
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
