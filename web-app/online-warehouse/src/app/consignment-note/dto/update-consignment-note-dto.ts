import {CounterpartyDto} from "../../counterparty/dto/counterparty.dto";
import {CarrierDto} from "../../carrier/dto/carrier.dto";
import {ConsignmentNoteGoodsDto} from "./consignment-note-goods-dto";
import {ConsignmentNoteType} from "./enum/consignment-note-type.enum";
import {DriverDto} from "../../carrier/driver/driver.dto";

export class UpdateConsignmentNoteDto {
  id: number;
  number: string;
  shipment: Date;
  counterpartyId: number;
  carrierId: number;
  driverId: number;
  vehicleNumber: string;
  consignmentNoteGoodsList: Array<ConsignmentNoteGoodsDto>;
  consignmentNoteType: ConsignmentNoteType;
  description: string;
}
