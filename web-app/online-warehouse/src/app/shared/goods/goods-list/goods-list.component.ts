import {Component, OnInit} from '@angular/core';
import {GoodService} from "../good.service";
import {GoodsDto} from "../goods.dto";

@Component({
  selector: 'app-goods-list',
  templateUrl: './goods-list.component.html',
  styleUrls: ['./goods-list.component.css']
})
export class GoodsListComponent implements OnInit {

  goodsList: Array<GoodsDto>;

  constructor(private goodsService: GoodService) {
  }

  ngOnInit() {
    this.goodsService.getAllCompanies(2).subscribe((goodsList: GoodsDto[]) => {
      this.goodsList = goodsList;
    })
  }

  clickOnGoods(goods: GoodsDto) {
    console.log(goods);
  }

}
