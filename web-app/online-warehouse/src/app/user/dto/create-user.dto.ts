import {AddressDto} from "../../shared/address/dto/address.dto";
import {AuthorityDto} from "./authority.dto";

export class CreateUserDto {
  username: string;
  password: string;
  firstname: string;
  lastname: string;
  patronymic: string;
  email: string;
  address: AddressDto;
  birth: Date;
  authorities: AuthorityDto[];
}
