import {WriteOffTypeEnum} from "./enum/write-off-type.enum";

export class WriteOffActGoodsDto {
  constructor(
    public goodsId: number,
    public writeOffType: WriteOffTypeEnum,
    public amount: number
  ) {

  }
}
