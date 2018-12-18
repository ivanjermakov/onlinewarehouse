import {Component, OnInit} from '@angular/core';
import 'rxjs/add/operator/first';
import 'rxjs/add/operator/map';

import {User} from '../_models';
import {UserService} from '../_services';
import {first} from "rxjs/operators";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Component({
  templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
  users: User[] = [];
  private error: any;

  constructor(private userService: UserService,
              private errorToast: RequestErrorToastHandlerService) {
  }

  ngOnInit() {
    // get users from secure api end point
    this.userService.getAll()
      .pipe(first())
      .subscribe((users) => {
          this.users = users;
        }, (err: any) => {
          this.error = err;
          this.errorToast.handleError(err)
        }
      );
  }

}
