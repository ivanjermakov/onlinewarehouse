import {GoodsDto} from "../../shared/goods/dto/goods.dto";
import {WriteOffTypeEnum} from "./enum/write-off-type.enum";

export class WriteOffActGoodsDto {
  goods: GoodsDto;
  writeOffType: WriteOffTypeEnum;
  amount: number;
}
