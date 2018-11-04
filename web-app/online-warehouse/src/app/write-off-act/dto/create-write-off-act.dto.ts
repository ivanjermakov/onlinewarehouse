import {WriteOffActTypeEnum} from "./enum/write-off-act-type.enum";
import {WriteOffActGoodsDto} from "./write-off-act-goods.dto";

export class CreateWriteOffActDto {
  constructor(
    public creatorId: number,
    public responsiblePerson: number,
    public writeOffActType: WriteOffActTypeEnum,
    public writeOffActGoodsDtoList: Array<WriteOffActGoodsDto>
  ) {
  }
}
