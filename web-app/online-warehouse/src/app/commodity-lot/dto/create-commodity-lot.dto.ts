class CreateCommodityLotDto {
  counterpartyId: number;
  commodityLotType: string;
  createCommodityLotGoodsDtoList: Array<CreateCommodityLotGoodsDto>;
}
