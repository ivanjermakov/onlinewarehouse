import {WriteOffActTypeEnum} from "./enum/write-off-act-type.enum";
import {CreateWriteOffActGoodsDto} from "./create-write-off-act-goods.dto";

export class CreateWriteOffActDto {
  constructor(
    public creatorId: number,
    public responsiblePerson: number,
    public writeOffActType: WriteOffActTypeEnum,
    public writeOffActGoodsDtoList: Array<CreateWriteOffActGoodsDto>
  ) {
  }
}
