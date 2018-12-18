import {AddressDto} from "../../shared/address/dto/address.dto";
import {PlacementDto} from "./placement.dto";

export class WarehouseDto {
  id: number;
  name: string;
  address: AddressDto;
  placements: Array<PlacementDto>;
}
