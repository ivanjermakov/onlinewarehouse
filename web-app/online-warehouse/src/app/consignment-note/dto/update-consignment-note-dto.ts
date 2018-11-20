import {CounterpartyDto} from "../../counterparty/dto/counterparty.dto";
import {CompanyDto} from "../../company/dto/company.dto";
import {CarrierDto} from "../../carrier/dto/carrier.dto";
import {ConsignmentNoteGoodsDto} from "./consignment-note-goods-dto";
import {ConsignmentNoteType} from "./enum/consignment-note-type.enum";
import {ConsignmentNoteStatus} from "./enum/consignment-note-status.enum";

export class UpdateConsignmentNoteDto {
  id: number;
  number: string;
  company: CompanyDto;
  shipment: Date;
  counterparty: CounterpartyDto;
  carrier: CarrierDto;
  creatorId: number;
  vehicleNumber: string;
  registration: Date;
  consignmentNoteGoodsList: Array<ConsignmentNoteGoodsDto>;
  consignmentNoteType: ConsignmentNoteType;
  consignmentNoteStatus: ConsignmentNoteStatus;
  description: string;
}
