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
          },
          {
            'name': 'Список товаров компании',
            'url': '/list-goods'
          },
          {
            'name': 'Список товарных партий',
            'url': '/list-commodity-lots'
          },

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
          },
          {
            'name': 'Список перевозчиков',
            'url': 'list-carriers'
          },
          {
            'name': 'Список контрагентов',
            'url': 'list-counterparties'
          }
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
            'url': 'create-write-off-act'
          }
          , {
            'name': 'Список актов',
            'url': 'list-write-off-acts'
          }

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
