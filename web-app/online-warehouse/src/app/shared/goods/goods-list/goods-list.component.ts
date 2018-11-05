import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {GoodService} from "../good.service";
import {GoodsDto} from "../goods.dto";
import {PageEvent} from "@angular/material";
import {Page} from "../../pagination/page";
import {Pageable} from "../../pagination/pageable";

@Component({
  selector: 'app-goods-list',
  templateUrl: './goods-list.component.html',
  styleUrls: ['./goods-list.component.css']
})
export class GoodsListComponent implements OnInit {

  @Output() goodsSelected: EventEmitter<GoodsDto> = new EventEmitter();
  page: Page<GoodsDto>;
  pageable: Pageable = new Pageable(0, 2);
  pageSizeOptions: number[] = [2, 3, 5];

  constructor(private goodsService: GoodService) {
  }

  ngOnInit() {
    this.getGoods();
  }

  pageChanged(event: PageEvent) {
    this.page = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.getGoods();

  }

  clickOnGoods(goods: GoodsDto) {
    this.goodsSelected.emit(goods);
  }

  private getGoods() {
    this.goodsService.getAllGoods(2, this.pageable).subscribe((page: Page<GoodsDto>) => {
      this.page = page;
    });

  }

}
