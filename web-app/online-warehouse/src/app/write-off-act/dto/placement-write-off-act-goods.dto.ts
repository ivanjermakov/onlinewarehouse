import {WriteOffTypeEnum} from "./enum/write-off-type.enum";
import {PlacementGoodsDto} from "../../warehouse/dto/placement-goods.dto";

export class PlacementWriteOffActGoodsDto {
  placementGoods: PlacementGoodsDto;
  writeOffType: WriteOffTypeEnum;
  amount: number;
}
