import {GoodsDto} from "../../shared/goods/dto/goods.dto";

export class PlacementGoodsDto {
  id: number;
  amount: number;
  goods: GoodsDto;
  counterpartyId: number;
  storageTimeDays: number;
  expirationDate: Date;

  constructor(id?: number, amount?: number, goods?: GoodsDto, counterpartyId?: number, storageTimeDays?: number, expirationDate?: Date) {
    this.id = id;
    this.amount = amount;
    this.goods = goods;
    this.counterpartyId = counterpartyId;
    this.storageTimeDays = storageTimeDays;
    this.expirationDate = expirationDate;
  }
}
