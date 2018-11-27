import {CompanyDto} from "../../company/dto/company.dto";
import {CarrierDto} from "../../carrier/dto/carrier.dto";
import {CounterpartyDto} from "../../counterparty/dto/counterparty.dto";
import {ConsignmentNoteGoodsDto} from "./consignment-note-goods-dto";
import {DriverDto} from "../../carrier/driver/driver.dto";
import {ConsignmentNoteType} from "./enum/consignment-note-type.enum";
import {ConsignmentNoteStatus} from "./enum/consignment-note-status.enum";
import {UserDto} from "../../user/dto/user.dto";

export class ConsignmentNoteDto {
  id: number;
  company: CompanyDto;
  number: string;
  shipment: Date;
  counterparty: CounterpartyDto;
  carrier: CarrierDto;
  driver: DriverDto;
  vehicleNumber: string;
  registration: Date;
  consignmentNoteGoodsList: Array<ConsignmentNoteGoodsDto>;
  consignmentNoteType: ConsignmentNoteType;
  consignmentNoteStatus: ConsignmentNoteStatus;
  description: string;
}
