import {CompanySizeEnum} from "./enum/company-size.enum";
import {CreateUserDto} from "../../user/dto/create-user.dto";

export class CreateCompanyDto {
  constructor(
    public name: string,
    public sizeType: CompanySizeEnum,
    public createUserDto: CreateUserDto
  ) {
  }
}
