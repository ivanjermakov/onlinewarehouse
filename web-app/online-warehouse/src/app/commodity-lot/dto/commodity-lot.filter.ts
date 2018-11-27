import {CommodityLotTypeEnum} from "./enum/commodity-lot-type.enum";
import {CommodityLotStatusEnum} from "./enum/commodity-lot-status.enum";

export class CommodityLotFilter {
  commodityLotType: CommodityLotTypeEnum;
  commodityLotStatus: CommodityLotStatusEnum;
  from: Date;
  to: Date;
  counterpartyName: string;

  constructor(commodityLotStatus?: CommodityLotStatusEnum, commodityLotType?: CommodityLotTypeEnum, from?: Date, to?: Date, counterpartyName?: string) {
    this.commodityLotStatus = commodityLotStatus;
    this.commodityLotType = commodityLotType;
    this.from = from;
    this.to = to;
    this.counterpartyName = counterpartyName;
  }
}
