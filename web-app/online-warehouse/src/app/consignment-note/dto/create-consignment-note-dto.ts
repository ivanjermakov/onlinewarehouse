import {CounterpartyDto} from "../../counterparty/dto/counterparty.dto";
import {CompanyDto} from "../../company/dto/company.dto";
import {CarrierDto} from "../../carrier/dto/carrier.dto";
import {ConsignmentNoteGoodsDto} from "./consignment-note-goods-dto";
import {ConsignmentNoteType} from "./enum/consignment-note-type.enum";
import {DriverDto} from "../../carrier/driver/driver.dto";

export class CreateConsignmentNoteDto {
  number: string;
  shipment: Date;
  counterparty: CounterpartyDto;
  carrier: CarrierDto;
  driver: DriverDto;
  creatorId: number;
  vehicleNumber: string;
  consignmentNoteGoodsList: Array<ConsignmentNoteGoodsDto>;
  consignmentNoteType: ConsignmentNoteType;
  description: string;
}
