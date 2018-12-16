import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from "../auth/_services";
import {animate, state, style, transition, trigger} from "@angular/animations";
import {Router} from "@angular/router";
import {WebSocketService} from "../auth/_services/web-socket.service";
import {ReportService} from "../report/service/report.service";

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
    private report: ReportService,
    private webSocket: WebSocketService
  ) {
  }

  ngOnInit() {
    this.auth.getAuthorities().forEach(auth =>
      this.userAuthorities.push(auth.authority)
    );
    this.webSocket.connect();
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
    this.auth.logout();
    this.router.navigate(['/login']);
    this.webSocket.disconnect();
  }


  getClientReport() {
    this.report.getClientReport(null);
  }
}
