import {CounterpartyDto} from "../../counterparty/dto/counterparty.dto";
import {CompanyDto} from "../../company/dto/company.dto";
import {CarrierDto} from "../../carrier/dto/carrier.dto";
import {ConsignmentNoteGoodsDto} from "./consignment-note-goods-dto";

export class CreateConsignmentNoteDto {
  number: string;
  shipment: string;
  counterparty: CounterpartyDto;
  carrier: CarrierDto;
  vehicleNumber: string;
  registration: string;
  consignmentNoteGoodsList: Array<ConsignmentNoteGoodsDto>;
  consignmentNoteType: string;
  consignmentNoteStatus: string;
  description: string;
}
