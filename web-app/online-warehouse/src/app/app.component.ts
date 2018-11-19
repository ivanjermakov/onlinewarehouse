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
            'url': 'list-companies'
          },
          {
            'name': 'Создать компанию',
            'url': 'create-company'
          }
        ]
      },
      {
        'name': 'company admin',
        'actions': [
          {
            'name': 'Посмотреть пользователей',
            'url': 'list-users'
          },
          {
            'name': 'Создать пользователя',
            'url': 'user'
          },
          {
            'name': 'Список товаров компании',
            'url': 'list-goods'
          },
          {
            'name': 'Список товарных партий',
            'url': 'list-commodity-lots'
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
            'name': 'Зарегистрировать ТТН',
            'url': 'register-consignment-note'
          },
          {
            'name': 'Посмотреть ТТН',
            'url': 'consignment-notes'
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
          },
          {
            'name': 'Список складов',
            'url': 'list-warehouses'
          },
          {
            'name': 'Создать склад',
            'url': 'create-warehouse'
          }
        ]
      },
      {
        'name': 'inspector',
        'actions': [
          {
            'name': 'Зарегистрированные ТТН',
            'url': 'registered-consignment-notes'
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
