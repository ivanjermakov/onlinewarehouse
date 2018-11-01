class CommodityLotListDto {
  id: number;
  counterpartyName: string;
  creation: Date;
  commodityLotType: string;

  constructor(id: number, counterpartyName: string, creation: Date, commodityLotType: string) {
    this.id = id;
    this.counterpartyName = counterpartyName;
    this.creation = creation;
    this.commodityLotType = commodityLotType;
  }
}
