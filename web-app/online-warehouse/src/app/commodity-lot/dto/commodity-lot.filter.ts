class CommodityLotFilter {
  commodityLotType: string;
  from: Date;
  to: Date;
  counterpartyName: string;

  constructor(commodityLotType: string, from: Date, to: Date, counterpartyName: string) {
    this.commodityLotType = commodityLotType;
    this.from = from;
    this.to = to;
    this.counterpartyName = counterpartyName;
  }
}
