import {CompanyDto} from "../../company/dto/company.dto";

export class ConsignmentNoteListDto {
  id: number;
  number: string;
  company: CompanyDto;
  registration: Date;
  consignmentNoteType: string;
  consignmentNoteStatus: string;
}
