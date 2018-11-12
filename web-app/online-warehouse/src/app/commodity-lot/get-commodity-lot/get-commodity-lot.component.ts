import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CommodityLotDto} from "../dto/commodity-lot.dto";
import {CommodityLotService} from "../service/commodity-lot.service";
import {BehaviorSubject, of} from "rxjs";
import {catchError, finalize} from "rxjs/operators";

@Component({
  selector: 'app-get-commodity-lot',
  templateUrl: './get-commodity-lot.component.html',
  styleUrls: ['./get-commodity-lot.component.css']
})
export class GetCommodityLotComponent implements OnInit {
  commodityLotId: number;
  commodityLotDto: CommodityLotDto;
  displayedColumns = ["amount", "name", "placementType", "measurementUnit", "cost", "weight", "labelling", "description"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();

  private errors: any[];

  constructor(
    private commodityLotService: CommodityLotService,
    private route: ActivatedRoute) {
  }

  ngOnInit() {
    if (this.idExist()) {
      this.getCommodityLot();
    } else {
    }
  }

  idExist(): boolean {
    if (this.route.snapshot.paramMap.get('id') !== null) {
      this.commodityLotId = +this.route.snapshot.paramMap.get('id');
      return true;
    } else {
      return false;
    }
  }

  getCommodityLot() {
    this.loadingSubject.next(true);
    this.commodityLotService.getCommodityLot(2, this.commodityLotId)
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe(commodityLotDto => {
        if (commodityLotDto instanceof Array) {
          this.errors = commodityLotDto as any[];
        } else {
          this.commodityLotDto = commodityLotDto;
        }
      });
  }


}
