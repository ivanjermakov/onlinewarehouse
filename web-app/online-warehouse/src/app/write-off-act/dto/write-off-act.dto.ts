import {WriteOffActGoodsDto} from "./write-off-act-goods.dto";
import {WriteOffActTypeEnum} from "./enum/write-off-act-type.enum";

export class WriteOffActDto {
  private id: number;
  private creation: Date;
  private creatorId: number;
  private responsiblePerson: string;
  private totalAmount: number;
  private writeOffActType: WriteOffActTypeEnum;
  private writeOffActGoodsList: WriteOffActGoodsDto;

}
