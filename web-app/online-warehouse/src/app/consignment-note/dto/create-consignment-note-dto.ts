import {CounterpartyDto} from "../../counterparty/dto/counterparty.dto";
import {CompanyDto} from "../../company/dto/company.dto";

export class CreateConsignmentNoteDto {
  number: string;
  shipment: string;
  counterparty: CounterpartyDto;
  carrier: CarrierDto;
  vehicleNumber: string;

  company: CompanyDto;
  // user: UserDto;
  registration: string;
  consignmentNoteType: string;
}
