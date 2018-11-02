import {CompanySizeEnum} from "./enum/company-size.enum";

export class CreateCompanyDto {
  constructor(
    public name: string,
    public sizeType: CompanySizeEnum
  ) {
  }
}
