import {Component, OnInit} from '@angular/core';
import {UserService} from '../user.service';

@Component({
  selector: 'app-view-users',
  templateUrl: './view-users.component.html',
  styleUrls: ['./view-users.component.css']
})
export class ViewUsersComponent implements OnInit {
  users;

  constructor(private _userService: UserService) {
  }

  ngOnInit() {
    this._userService.getAllUsers(1).subscribe(users => this.users = users);
  }
}
