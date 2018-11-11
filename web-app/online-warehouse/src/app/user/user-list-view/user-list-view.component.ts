import {Component, OnInit} from '@angular/core';
import {UserDto} from "../dto/user.dto";

@Component({
  selector: 'app-user-list-view',
  templateUrl: './user-list-view.component.html',
  styleUrls: ['./user-list-view.component.css']
})
export class UserListViewComponent implements OnInit {

  private user: UserDto;

  constructor() {
  }

  ngOnInit() {
  }

  selected(user: UserDto) {
    this.user = user;
  }

  backToList() {
    this.user = null;
  }

}
