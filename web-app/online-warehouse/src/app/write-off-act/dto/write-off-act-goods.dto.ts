import {GoodsDto} from "../../shared/goods/dto/goods.dto";
import {WriteOffTypeEnum} from "./enum/write-off-type.enum";

export class WriteOffActGoodsDto {
  private goods: GoodsDto;
  private writeOffType: WriteOffTypeEnum;
  private amount: number;
}
