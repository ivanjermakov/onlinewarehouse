import {Component, OnInit} from '@angular/core';
import {CarrierListDto} from "../dto/carrier-list.dto";
import {Router} from "@angular/router";

@Component({
  selector: 'app-carrier-list-view',
  templateUrl: './carrier-list-view.component.html',
  styleUrls: ['./carrier-list-view.component.css']
})
export class CarrierListViewComponent implements OnInit {

  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  selected(carrier: CarrierListDto) {
    this.router.navigate(['carrier/' + carrier.id]);
  }

}
