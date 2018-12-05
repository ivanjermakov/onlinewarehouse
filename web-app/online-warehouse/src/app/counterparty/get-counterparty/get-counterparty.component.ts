import {Component, OnInit} from '@angular/core';
import {CounterpartyDto} from "../dto/counterparty.dto";
import {ActivatedRoute, Router} from "@angular/router";
import {BehaviorSubject} from "rxjs";
import {finalize} from "rxjs/operators";
import {CounterpartyService} from "../service/counterparty.service";
import {CounterpartyTypeEnum} from "../dto/enum/counterparty-type.enum";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Component({
  selector: 'app-get-counterparty',
  templateUrl: './get-counterparty.component.html',
  styleUrls: ['./get-counterparty.component.css']
})
export class GetCounterpartyComponent implements OnInit {

  private counterpartyType = CounterpartyTypeEnum;
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private counterparty: CounterpartyDto;


  constructor(private counterpartyService: CounterpartyService,
              private router: Router,
              private route: ActivatedRoute,
              private errorToast: RequestErrorToastHandlerService) {
  }

  ngOnInit(): void {
    this.getConsignmentNote();
  }

  getConsignmentNote(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!Number.isNaN(id) && id != 0) {
      this.loadingSubject.next(true);
      this.counterpartyService.getCounterparty(id)
        .pipe(finalize(() => this.loadingSubject.next(false)))
        .subscribe((counterparty) => {
            this.counterparty = counterparty;
          }, (err: any) => {
            this.errorToast.handleError(err);
          }
        );
    }
  }
}
