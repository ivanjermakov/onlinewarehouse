class CommodityLotGoodsDto {
  amount: number;
  goodsId: number;
  goodsName: string;
  goodsPlacementType: string;
  goodsMeasurementUnit: string;

  constructor(amount: number, goodsId: number, goodsName: string, goodsPlacementType: string, goodsMeasurementUnit: string) {
    this.amount = amount;
    this.goodsId = goodsId;
    this.goodsName = goodsName;
    this.goodsPlacementType = goodsPlacementType;
    this.goodsMeasurementUnit = goodsMeasurementUnit;
  }
}
