class CommodityLotDto {
  id: number;
  counterpartyId: number;
  commodityLotType: string;
  creation: Date;
  commodityLotGoodsDtoList: Array<CommodityLotGoodsDto>;

  constructor(id: number, counterpartyId: number, commodityLotType: string, creation: Date, commodityLotGoodsDtoList: Array<CommodityLotGoodsDto>) {
    this.id = id;
    this.counterpartyId = counterpartyId;
    this.commodityLotType = commodityLotType;
    this.creation = creation;
    this.commodityLotGoodsDtoList = commodityLotGoodsDtoList;
  }
}
