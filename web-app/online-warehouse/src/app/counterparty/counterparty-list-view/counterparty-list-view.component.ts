import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {CounterpartyDto} from "../dto/counterparty.dto";

@Component({
  selector: 'app-counterparty-list-view',
  templateUrl: './counterparty-list-view.component.html',
  styleUrls: ['./counterparty-list-view.component.css']
})
export class CounterpartyListViewComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  selected(counterparty: CounterpartyDto) {
    this.router.navigate(['app/counterparty/' + counterparty.id]);
  }

  newCounterparty() {
    this.router.navigate(['app/create-counterparty']);
  }
}
