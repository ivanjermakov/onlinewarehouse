import {CounterpartyTypeEnum} from './enum/counterparty-type.enum';
import {AddressDto} from "../../shared/address/dto/address.dto";

export class CounterpartyDto {
  id: number;
  address: AddressDto;
  name: string;
  counterpartyType: CounterpartyTypeEnum;
  taxNumber: string;


  // constructor(id: number, address: AddressDto, name: string, counterpartyType: CounterpartyTypeEnum, taxNumber: string) {
  //   this.id = id;
  //   this.address = address;
  //   this.name = name;
  //   this.counterpartyType = counterpartyType;
  //   this.taxNumber = taxNumber;
  // }
}
