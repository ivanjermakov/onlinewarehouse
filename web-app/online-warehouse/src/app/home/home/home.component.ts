import {Component, OnInit} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {FormBuilder} from "@angular/forms";
import {Router} from "@angular/router";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";
import {HomeService} from "../service/home.service";
import {HomeCard} from "../home-card";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private homeCards: HomeCard[];

  constructor(private homeService: HomeService,
              private fb: FormBuilder,
              private router: Router,
              private errorToast: RequestErrorToastHandlerService) {
  }

  ngOnInit() {
    this.getHomeCards();
  }

  getHomeCards() {
    this.homeService.getHomeCards()
      .subscribe((homeCards) => {
          this.homeCards = homeCards;
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }


}
