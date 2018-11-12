import {AddressDto} from "../../shared/address/dto/address.dto";
import {AuthorityDto} from "./authority.dto";

export class UserDto {
  id: string;
  username: string;
  firstname: string;
  lastname: string;
  patronymic: string;
  address: AddressDto;
  birth: Date;
  enabled: boolean;
  authorities: Array<AuthorityDto>;
}
