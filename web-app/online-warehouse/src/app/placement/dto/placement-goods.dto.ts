import {GoodsDto} from "../../shared/goods/dto/goods.dto";

export class PlacementGoodsDto {

  amount: number;
  goods: GoodsDto;
  counterpartyId: number;
  storageTimeDays: number;
}
