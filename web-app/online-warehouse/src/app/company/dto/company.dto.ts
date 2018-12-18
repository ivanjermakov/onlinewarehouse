import {CompanySizeEnum} from "./enum/company-size.enum";
import {ActionTypeEnum} from "./enum/action-type.enum";

export class CompanyDto {
  public id: number;
  public name: string;
  public sizeType: CompanySizeEnum;
  public change: Date;
  public actionType: ActionTypeEnum;

  constructor(
    id: number,
    name: string,
    sizeType: CompanySizeEnum,
    change: Date,
    actionType: ActionTypeEnum
  ) {
    this.id = id;
    this.name = name;
    this.sizeType = sizeType;
    this.change = change;
    this.actionType = actionType;
  }
}
