import {CompanyDto} from "../../company/dto/company.dto";
import {CounterpartyDto} from "../../counterparty/dto/counterparty.dto";

export class ConsignmentNoteDto {
  id: number;
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
