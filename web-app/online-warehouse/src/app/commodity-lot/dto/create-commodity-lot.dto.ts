class CreateCommodityLotDto {
  counterpartyId: number;
  commodityLotType: string;
  createCommodityLotGoodsDtoList: Array<CreateCommodityLotGoodsDto>;


  constructor(counterpartyId: number, commodityLotType: string, createCommodityLotGoodsDtoList: Array<CreateCommodityLotGoodsDto>) {
    this.counterpartyId = counterpartyId;
    this.commodityLotType = commodityLotType;
    this.createCommodityLotGoodsDtoList = createCommodityLotGoodsDtoList;
  }
}
