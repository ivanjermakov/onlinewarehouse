import {CarrierTypeEnum} from "./enum/carrier-type.enum";
import {AddressDto} from "../../shared/address/dto/address.dto";

export class CarrierDto {
  id: number;
  address: AddressDto;
  name: string;
  taxNumber: string;
  carrierType: CarrierTypeEnum;
  trusted: boolean;
  driverInfo: string[];
}
