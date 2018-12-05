import {FormControl} from "@angular/forms";
import {Injectable} from "@angular/core";
import {UserService} from "./user.service";

@Injectable()
export class UsernameValidator {

  debouncer: any;

  constructor(public userService: UserService) {

  }

  checkUsername(control: FormControl): any {

    clearTimeout(this.debouncer);

    return new Promise(resolve => {

      this.debouncer = setTimeout(() => {

        this.userService.validateUsername(control.value).subscribe((canUse) => {
          if (canUse) {
            resolve(null);
          } else {
            resolve({'usernameInUse': true});
          }
        }, (err) => {
          resolve({'usernameInUse': true});
        });

      }, 1000);

    });
  }

}
