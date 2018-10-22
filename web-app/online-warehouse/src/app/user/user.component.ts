import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {UserService} from '../user.service';
import {FormControl, FormGroup} from '@angular/forms';
import {Location} from '@angular/common';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user: any = {
    id: null,
    firstName: null,
    lastName: null,
    patronymic: null,
    address: {
      id: null,
      country: null,
      region: null,
      locality: null
    },
    company: null,
    birth: null,
    email: null
  };
  private userId: number;
  userForm: FormGroup = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    patronymic: new FormControl(''),
    country: new FormControl(''),
    region: new FormControl(''),
    locality: new FormControl(''),
    birthDate: new FormControl(''),
    email: new FormControl('')
  });

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private location: Location
  ) {
  }

  ngOnInit() {
    if (this.idExist()) {
      this.getUserByIdAndSetFields();
    } else {
    }
  }

  idExist(): boolean {
    if (this.route.snapshot.paramMap.get('id') !== null) {
      this.userId = +this.route.snapshot.paramMap.get('id');
      return true;
    } else {
      return false;
    }
  }

  private getUserByIdAndSetFields(): void {
    this.userService.getUserById(1, this.userId).subscribe(user => {
      this.user = user;
      this.setUserFields();
    });
  }

  private setUserFields(): void {
    this.userForm = new FormGroup({
      firstName: new FormControl(this.user.firstName),
      lastName: new FormControl(this.user.lastName),
      patronymic: new FormControl(this.user.patronymic),
      country: new FormControl(this.user.address.country),
      region: new FormControl(this.user.address.region),
      locality: new FormControl(this.user.address.locality),
      birthDate: new FormControl(this.user.birth.join('-')),
      email: new FormControl(this.user.email)
    });
  }

  goBack(): void {
    this.location.back();
  }

  save() {
    const resultUserForm = this.userForm.value;
    this.user.firstName = resultUserForm.firstName;
    this.user.lastName = resultUserForm.lastName;
    this.user.patronymic = resultUserForm.patronymic;
    this.user.address.country = resultUserForm.country;
    this.user.address.region = resultUserForm.region;
    this.user.address.locality = resultUserForm.locality;
    this.user.birth = resultUserForm.birthDate;
    this.user.email = resultUserForm.email;
    console.log(this.user);
  }
}
