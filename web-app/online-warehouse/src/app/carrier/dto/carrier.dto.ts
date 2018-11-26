import {CarrierTypeEnum} from "./enum/carrier-type.enum";
import {AddressDto} from "../../shared/address/dto/address.dto";
import {DriverDto} from "../driver/driver.dto";

export class CarrierDto {
  id: number;
  address: AddressDto;
  name: string;
  taxNumber: string;
  carrierType: CarrierTypeEnum;
  trusted: boolean;
  drivers: Array<DriverDto> = [];
}
