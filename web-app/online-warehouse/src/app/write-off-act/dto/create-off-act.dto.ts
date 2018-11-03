import {CompanyDto} from '../../company/dto/company.dto';
import {WriteOffActType} from '../enum/write-off-act-type.enum';

export class CreateWriteOffActDto {
  company: CompanyDto;
  creation: Date;
  // TODO: use UserDto
  creator: string;
  responsiblePerson: string;
  totalAmount: number;
  writeOffActType: WriteOffActType;


  constructor(company: CompanyDto, creation: Date, creator: string, responsiblePerson: string, totalAmount: number,
              writeOffActType: WriteOffActType) {
    this.company = company;
    this.creation = creation;
    this.creator = creator;
    this.responsiblePerson = responsiblePerson;
    this.totalAmount = totalAmount;
    this.writeOffActType = writeOffActType;
  }
}
