import {CompanyDto} from "../../company/dto/company.dto";
import {CounterpartyDto} from "../../counterparty/dto/counterparty.dto";

export class ConsignmentNoteDto {
  id: number;
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
