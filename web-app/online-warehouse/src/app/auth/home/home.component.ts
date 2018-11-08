import { Component, OnInit } from '@angular/core';
import 'rxjs/add/operator/first';
import 'rxjs/add/operator/map';

import { User } from '../_models/index';
import { UserService } from '../_services/index';
import {first} from "rxjs/operators";

@Component({
    templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
    users: User[] = [];

    constructor(private userService: UserService) { }

    ngOnInit() {
        // get users from secure api end point
        this.userService.getAll()
            .pipe(first())
            .subscribe(users => {
                this.users = users;
            });
    }

}
