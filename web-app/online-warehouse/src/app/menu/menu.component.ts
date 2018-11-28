import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../auth/_services";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {Router} from "@angular/router";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  animations: [
    trigger('openClose', [
      state('open', style({
        display: 'block!important',
      })),
      state('closed', style({
        width: 0,
        display: 'none',
        visibility: 'hidden',
        opacity: 0,
      })),
      transition('closed <=> open', [
        style({
          position: 'relative',
          visibility: 'hidden',
          overflow: 'hidden'
        }),
        animate('500ms ease')
      ])
    ]),
  ],
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  private isOpenArr: Array<boolean> = [false, false, false, false, false, false];

  private userAuthorities: Array<String> = new Array<String>();

  constructor(
    private auth: AuthenticationService,
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
  }

  ngOnInit() {
    this.auth.getAuthorities().forEach(auth =>
      this.userAuthorities.push(auth.authority)
    )
  }

  closeAll(){
    this.isOpenArr.forEach((value, index, array) => {
      array[index] = false;
    });
  }

  toggle(toggle: number) {
    this.isOpenArr.forEach((value, index, array) => {
      if (toggle === index) {
        array[index] = !value;
      } else {
        array[index] = false
      }
    });
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

}
