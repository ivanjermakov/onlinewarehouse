import {GoodsDto} from "../../shared/goods/goods.dto";

export class PlacementGoodsDto {

  amount: number;
  goods: GoodsDto;
  counterpartyId: number;
  storageTimeDays: number;
}
