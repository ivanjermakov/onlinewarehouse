import {CommodityLotTypeEnum} from "./enum/commodity-lot-type.enum";

export class CommodityLotListDto {
  id: number;
  counterpartyName: string;
  creation: Date;
  commodityLotType: CommodityLotTypeEnum;

  constructor(id: number, counterpartyName: string, creation: Date, commodityLotType: CommodityLotTypeEnum) {
    this.id = id;
    this.counterpartyName = counterpartyName;
    this.creation = creation;
    this.commodityLotType = commodityLotType;
  }
}
