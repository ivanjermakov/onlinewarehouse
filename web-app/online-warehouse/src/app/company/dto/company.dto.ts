import {CompanySizeEnum} from "./enum/company-size.enum";
import {ActionTypeEnum} from "./enum/action-type.enum";

export class CompanyDto {
  constructor(
    public id: number,
    public name: string,
    public sizeType: CompanySizeEnum,
    public change: Date,
    public actionType: ActionTypeEnum
  ) {
  }
}
