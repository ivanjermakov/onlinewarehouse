import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {CommodityLotDto} from "../dto/commodity-lot.dto";
import {CommodityLotService} from "../service/commodity-lot.service";
import {BehaviorSubject, of} from "rxjs";
import {catchError, finalize} from "rxjs/operators";
import {CommodityLotTypeEnum} from "../dto/enum/commodity-lot-type.enum";
import {CommodityLotStatusEnum} from "../dto/enum/commodity-lot-status.enum";
import {PlacementTypeEnum} from "../../shared/enum/placement-type.enum";
import {CommodityLotGoodsDto} from "../dto/commodity-lot-goods.dto";

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

  private commodityLotTypeEnum = CommodityLotTypeEnum;
  private commodityLotStatusEnum = CommodityLotStatusEnum;
  private placementTypeEnum = PlacementTypeEnum;

  private totalCost: number = 0;
  private totalWeight: number = 0;

  private errors: any[];

  constructor(
    private commodityLotService: CommodityLotService,
    private route: ActivatedRoute,
    private router: Router) {
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
    this.commodityLotService.getCommodityLot(this.commodityLotId)
      .pipe(
        catchError(() => of([])),
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe(commodityLotDto => {
        if (commodityLotDto instanceof Array) {
          this.errors = commodityLotDto as any[];
        } else {
          this.commodityLotDto = commodityLotDto;
          this.getTotal();
        }
      });
  }

  navigateToCounterparty(i: number) {
    this.router.navigate(['app/counterparty/' + i]);
  }

  getTotal() {
    this.commodityLotDto.commodityLotGoodsList.forEach((goods: CommodityLotGoodsDto) => {
      this.totalCost += goods.amount * goods.goods.cost;
      this.totalWeight += goods.amount * goods.goods.weight;
    })
  }
}
