import {WriteOffActTypeEnum} from "./enum/write-off-act-type.enum";

export class WriteOffActFilter {
  public writeOffActType: WriteOffActTypeEnum;
  public from: Date;
  public to: Date;


  constructor(writeOffActType?: WriteOffActTypeEnum, from?: Date, to?: Date) {
    this.writeOffActType = writeOffActType;
    this.from = from;
    this.to = to;
  }
}
