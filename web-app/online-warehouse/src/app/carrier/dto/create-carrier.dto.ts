import {AddressDto} from "../../shared/address/dto/address.dto";

export class CreateCarrierDto {
  address: AddressDto;
  name: string;
  taxNumber: string;
  carrierType: string;
  trusted: boolean;


  constructor(address?: AddressDto, name?: string, taxNumber?: string, carrierType?: string, trusted?: boolean) {
    this.address = address;
    this.name = name;
    this.taxNumber = taxNumber;
    this.carrierType = carrierType;
    this.trusted = trusted;
  }
}
