import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-left',
  templateUrl: './left.component.html',
  styleUrls: ['./left.component.css']
})
export class LeftComponent implements OnInit {

  @Input() currentMenuName;
  @Output() onClick = new EventEmitter();

  menu = {
    'roles': [
      {
        'name': 'admin',
        'actions': [
          {
            'name': 'name1',
            'component': 'componentName'
          },
          {
            'name': 'name2',
            'component': 'componentName2'
          },
          {
            'name': 'name3',
            'component': 'componentName3'
          }
        ]
      },
      {
        'name': 'user',
        'actions': [
          {
            'name': 'name1',
            'component': 'componentName'
          },
          {
            'name': 'name2',
            'component': 'componentName2'
          },
          {
            'name': 'name3',
            'component': 'componentName3'
          }
        ]
      }
    ]
  };

  constructor() {
  }

  ngOnInit() {
  }

  chooseAction(component) {
    this.onClick.emit(component);
  }

}
