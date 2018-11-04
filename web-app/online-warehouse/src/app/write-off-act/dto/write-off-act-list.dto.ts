import {WriteOffActTypeEnum} from "./enum/write-off-act-type.enum";

export class WriteOffActListDto {
  constructor(
    public id: number,
    public creation: Date,
    public creatorId: number,
    public responsiblePerson: string,
    public totalAmount: number,
    public writeOffActType: WriteOffActTypeEnum
  ) {

  }
}
