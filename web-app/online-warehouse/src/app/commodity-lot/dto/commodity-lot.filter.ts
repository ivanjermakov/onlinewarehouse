import {CommodityLotTypeEnum} from "./enum/commodity-lot-type.enum";

export class CommodityLotFilter {
  commodityLotType: CommodityLotTypeEnum;
  from: Date;
  to: Date;
  counterpartyName: string;

  constructor(commodityLotType?: CommodityLotTypeEnum, from?: Date, to?: Date, counterpartyName?: string) {
    this.commodityLotType = commodityLotType;
    this.from = from;
    this.to = to;
    this.counterpartyName = counterpartyName;
  }
}
