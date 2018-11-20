import {WriteOffTypeEnum} from "./enum/write-off-type.enum";

export class CreateWriteOffActGoodsDto {

  goodsId: number;
  writeOffType: WriteOffTypeEnum;
  amount: number;

  constructor(goodsId: number, writeOffType: WriteOffTypeEnum, amount: number) {
    this.goodsId = goodsId;
    this.writeOffType = writeOffType;
    this.amount = amount;
  }
}
