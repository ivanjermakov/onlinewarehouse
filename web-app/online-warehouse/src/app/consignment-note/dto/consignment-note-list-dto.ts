import {CompanyDto} from "../../company/dto/company.dto";
import {ConsignmentNoteType} from "./enum/consignment-note-type.enum";
import {ConsignmentNoteStatus} from "./enum/consignment-note-status.enum";

export class ConsignmentNoteListDto {
  id: number;
  number: string;
  company: CompanyDto;
  registration: Date;
  consignmentNoteType: ConsignmentNoteType;
  consignmentNoteStatus: ConsignmentNoteStatus;
}
