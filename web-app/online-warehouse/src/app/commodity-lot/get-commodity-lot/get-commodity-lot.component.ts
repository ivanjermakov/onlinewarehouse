import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CommodityLotDto} from "../dto/commodity-lot.dto";
import {CommodityLotService} from "../service/commodity-lot.service";

@Component({
  selector: 'app-get-commodity-lot',
  templateUrl: './get-commodity-lot.component.html',
  styleUrls: ['./get-commodity-lot.component.css']
})
export class GetCommodityLotComponent implements OnInit {
  commodityLotId: number;
  commodityLotDto: CommodityLotDto;

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
    this.commodityLotService.getCommodityLot(2, this.commodityLotId)
      .subscribe((data: CommodityLotDto) => this.commodityLotDto = data);
  }


}
