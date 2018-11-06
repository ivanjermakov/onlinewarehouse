import {CompanyDto} from "../../company/dto/company.dto";

export class ConsignmentNoteListDto {
  number: string;
  company: CompanyDto;
  registration: string;
  consignmentNoteType: string;
  consignmentNoteStatus: string;
}
