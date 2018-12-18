import {CommodityLotTypeEnum} from "./enum/commodity-lot-type.enum";
import {CommodityLotStatusEnum} from "./enum/commodity-lot-status.enum";

export class CommodityLotListDto {
  id: number;
  counterpartyName: string;
  commodityLotStatus: CommodityLotStatusEnum;
  creation: Date;
  commodityLotType: CommodityLotTypeEnum;

  constructor(id: number, commodityLotStatus: CommodityLotStatusEnum, counterpartyName: string, creation: Date, commodityLotType: CommodityLotTypeEnum) {
    this.id = id;
    this.commodityLotStatus = commodityLotStatus;
    this.counterpartyName = counterpartyName;
    this.creation = creation;
    this.commodityLotType = commodityLotType;
  }
}
