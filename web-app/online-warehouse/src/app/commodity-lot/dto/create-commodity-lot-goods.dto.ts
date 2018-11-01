class CreateCommodityLotGoodsDto {
  amount: number;
  goodsId: number;


  constructor(amount: number, goodsId: number) {
    this.amount = amount;
    this.goodsId = goodsId;
  }
}
