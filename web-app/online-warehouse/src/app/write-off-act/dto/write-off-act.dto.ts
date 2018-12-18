import {WriteOffActGoodsDto} from "./write-off-act-goods.dto";
import {WriteOffActTypeEnum} from "./enum/write-off-act-type.enum";
import {UserDto} from "../../user/dto/user.dto";

export class WriteOffActDto {
  id: number;
  creation: Date;
  creator: UserDto;
  // creatorId: number;
  responsiblePerson: string;
  totalAmount: number;
  writeOffActType: WriteOffActTypeEnum;
  writeOffActGoodsList: Array<WriteOffActGoodsDto>;

}
