import {CounterpartyDto} from "../../counterparty/dto/counterparty.dto";
import {CompanyDto} from "../../company/dto/company.dto";

export class CreateConsignmentNoteDto {
  company: CompanyDto;
  number: string;
  shipment: string;
  counterparty: CounterpartyDto;
  carrier: CarrierDto;
  vehicleNumber: string;
  // creator: UserDto;
  registration: string;
  // consignmentNoteGoodsList: ConsignmentNoteGoodsDto[];
  consignmentNoteType: string;
  // driver: DriverDto;
  consignmentNoteStatus: string;
  description: string;
}
