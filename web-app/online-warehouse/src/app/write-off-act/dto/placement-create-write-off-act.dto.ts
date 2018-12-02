import {WriteOffActTypeEnum} from "./enum/write-off-act-type.enum";
import {PlacementWriteOffActGoodsDto} from "./placement-write-off-act-goods.dto";

export class PlacementCreateWriteOffActDto {
  public creatorId: number;
  public responsiblePerson: number;
  public writeOffActType: WriteOffActTypeEnum;
  public placementGoodsDtoList: Array<PlacementWriteOffActGoodsDto>;

  constructor(creatorId: number, responsiblePerson: number, writeOffActType: WriteOffActTypeEnum, placementGoodsDtoList: Array<PlacementWriteOffActGoodsDto>) {
    this.creatorId = creatorId;
    this.responsiblePerson = responsiblePerson;
    this.writeOffActType = writeOffActType;
    this.placementGoodsDtoList = placementGoodsDtoList;
  }
}
