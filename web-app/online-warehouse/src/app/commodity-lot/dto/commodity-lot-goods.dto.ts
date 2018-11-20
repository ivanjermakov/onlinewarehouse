import {GoodsDto} from "../../shared/goods/dto/goods.dto";

export class CommodityLotGoodsDto {
  amount: number;
  goods: GoodsDto;


  constructor(amount: number, goods: GoodsDto) {
    this.amount = amount;
    this.goods = goods;
  }
}
