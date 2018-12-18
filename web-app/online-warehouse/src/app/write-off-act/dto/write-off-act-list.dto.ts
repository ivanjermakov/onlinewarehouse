import {WriteOffActTypeEnum} from "./enum/write-off-act-type.enum";
import {UserDto} from "../../user/dto/user.dto";

export class WriteOffActListDto {
  constructor(
    public id: number,
    public creation: Date,
    public creator: UserDto,
    public responsiblePerson: string,
    public totalAmount: number,
    public writeOffActType: WriteOffActTypeEnum
  ) {

  }
}
