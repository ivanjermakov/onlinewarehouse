import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {CommodityLotListDto} from "../dto/commodity-lot-list.dto";
import {CommodityLotService} from "../service/commodity-lot.service";
import {CommodityLotFilter} from "../dto/commodity-lot.filter";
import {CommodityLotTypeEnum} from "../dto/enum/commodity-lot-type.enum";
import {Page} from "../../shared/pagination/page";
import {Pageable} from "../../shared/pagination/pageable";
import {PageEvent} from "@angular/material";
import {FormBuilder, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-commodity-lot-list',
  templateUrl: './commodity-lot-list.component.html',
  styleUrls: ['./commodity-lot-list.component.css']
})
export class CommodityLotListComponent implements OnInit {

  page: Page<CommodityLotListDto>;
  pageable: Pageable = new Pageable(0, 2);
  pageSizeOptions: number[] = [2, 3, 5];
  commodityLotFilter: CommodityLotFilter = new CommodityLotFilter();
  commodityLotFilterForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private commodityLotService: CommodityLotService,
    private router: Router) {
    this.commodityLotFilterForm = fb.group({
      "commodityLotType": [''],
      "from": [''],
      "to": [''],
      "counterpartyName": [''],
    });
  }

  ngOnInit() {
    this.getCommodityLots();
  }

  navigateToPage(commodityLotId: number) {
    this.router.navigate(['commodity-lot/' + commodityLotId]);
  }

  pageChanged(event: PageEvent) {
    this.page = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.getCommodityLots();

  }

  getCommodityLotTypes(): Array<string> {
    return Object.keys(CommodityLotTypeEnum);
  }

  onSubmit(commodityLotFilterForm: FormGroup): void {
    this.page = null;
    this.pageable.page = 0;
    let value = commodityLotFilterForm.value;
    Object.assign(this.commodityLotFilter, value);
    this.getCommodityLots();
  }

  private getCommodityLots() {
    this.commodityLotService
      .getCommodityLots(this.commodityLotFilter, this.pageable, 2)
      .subscribe((page: Page<CommodityLotListDto>) => {
        this.page = page;
      })
  }
}
