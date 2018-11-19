import {CommodityLotTypeEnum} from "./enum/commodity-lot-type.enum";
import {CommodityLotGoodsDto} from "./commodity-lot-goods.dto";

export class CommodityLotDto {
  id: number;
  counterpartyId: number;
  commodityLotType: CommodityLotTypeEnum;
  creation: Date;
  commodityLotGoodsList: Array<CommodityLotGoodsDto>;

  constructor(id?: number, counterpartyId?: number, commodityLotType?: CommodityLotTypeEnum, creation?: Date, commodityLotGoodsList?: Array<CommodityLotGoodsDto>) {
    this.id = id;
    this.counterpartyId = counterpartyId;
    this.commodityLotType = commodityLotType;
    this.creation = creation;
    this.commodityLotGoodsList = commodityLotGoodsList;
  }
}
