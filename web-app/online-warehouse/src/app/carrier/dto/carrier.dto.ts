import {CarrierTypeEnum} from "./enum/carrier-type.enum";
import {AddressDto} from "../../shared/address/dto/address.dto";

export class CarrierDto {
  id: number;
  address: AddressDto;
  name: string;
  taxNumber: string;
  carrierType: CarrierTypeEnum;
  trusted: boolean;
  driverInfo: Array<string>;

  constructor(id: number, address: AddressDto, name: string, taxNumber: string, carrierType: CarrierTypeEnum, trusted: boolean, driverInfo: Array<string>) {
    this.id = id;
    this.address = address;
    this.name = name;
    this.taxNumber = taxNumber;
    this.carrierType = carrierType;
    this.trusted = trusted;
    this.driverInfo = driverInfo;
  }
}
