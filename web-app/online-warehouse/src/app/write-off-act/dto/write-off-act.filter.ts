import {WriteOffActTypeEnum} from "./enum/write-off-act-type.enum";

export class WriteOffActFilter {
  constructor(
    public writeOffActType: WriteOffActTypeEnum,
    public from: Date,
    public to: Date) {
  }

}
