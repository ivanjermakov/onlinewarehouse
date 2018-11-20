import {WriteOffActGoodsDto} from "./write-off-act-goods.dto";
import {WriteOffActTypeEnum} from "./enum/write-off-act-type.enum";

export class WriteOffActDto {
  id: number;
  creation: Date;
  creatorId: number;
  responsiblePerson: string;
  totalAmount: number;
  writeOffActType: WriteOffActTypeEnum;
  writeOffActGoodsList: Array<WriteOffActGoodsDto>;

}
