import {AddressDto} from "../../shared/address/dto/address.dto";
import {CounterpartyTypeEnum} from "./enum/counterparty-type.enum";

export class CreateCounterpartyDto {
  address: AddressDto;
  companyId: number;
  name: string;
  counterpartyType: CounterpartyTypeEnum;
  taxNumber: string;


  constructor(address?: AddressDto, companyId?: number, name?: string, counterpartyType?: CounterpartyTypeEnum, taxNumber?: string) {
    this.address = address;
    this.companyId = companyId;
    this.name = name;
    this.counterpartyType = counterpartyType;
    this.taxNumber = taxNumber;
  }
}
