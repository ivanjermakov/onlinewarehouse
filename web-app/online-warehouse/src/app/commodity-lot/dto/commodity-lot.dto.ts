class CommodityLotDto {
  id: number;
  counterpartyId: number;
  commodityLotType: string;
  creation: Date;
  commodityLotGoodsDtoList: Array<CommodityLotGoodsDto>;
}
