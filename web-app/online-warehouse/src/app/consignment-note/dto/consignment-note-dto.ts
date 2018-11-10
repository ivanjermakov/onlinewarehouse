import {CompanyDto} from "../../company/dto/company.dto";
import {CarrierDto} from "../../carrier/dto/carrier.dto";
import {CounterpartyDto} from "../../counterparty/dto/counterparty.dto";
import {ConsignmentNoteGoodsDto} from "./consignment-note-goods-dto";

export class ConsignmentNoteDto {
  id: number;
  company: CompanyDto;
  number: string;
  shipment: string;
  counterparty: CounterpartyDto;
  carrier: CarrierDto;
  vehicleNumber: string;
  // creator: User;
  registration: string;
  consignmentNoteGoodsList: ConsignmentNoteGoodsDto[];
  consignmentNoteType: string;
  // driver: DriverDto;
  consignmentNoteStatus: string;
  description: string;
}
