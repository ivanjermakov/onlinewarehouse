import {CommodityLotTypeEnum} from "./enum/commodity-lot-type.enum";
import {CommodityLotGoodsDto} from "./commodity-lot-goods.dto";
import {CommodityLotStatusEnum} from "./enum/commodity-lot-status.enum";

export class CommodityLotDto {
  id: number;
  counterpartyId: number;
  commodityLotStatus: CommodityLotStatusEnum;
  commodityLotType: CommodityLotTypeEnum;
  creation: Date;
  commodityLotGoodsList: Array<CommodityLotGoodsDto>;

  constructor(id?: number, counterpartyId?: number, commodityLotStatus?: CommodityLotStatusEnum, commodityLotType?: CommodityLotTypeEnum, creation?: Date, commodityLotGoodsList?: Array<CommodityLotGoodsDto>) {
    this.id = id;
    this.counterpartyId = counterpartyId;
    this.commodityLotType = commodityLotType;
    this.commodityLotStatus = commodityLotStatus;
    this.creation = creation;
    this.commodityLotGoodsList = commodityLotGoodsList;
  }
}
