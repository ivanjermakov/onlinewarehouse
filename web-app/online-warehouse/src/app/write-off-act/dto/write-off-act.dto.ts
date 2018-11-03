import {CompanyDto} from '../../company/dto/company.dto';
import {WriteOffActType} from '../enum/write-off-act-type.enum';

class WriteOffActDto {
  id: number;
  company: CompanyDto;
  creation: Date;
  // TODO: use UserDto
  creator: string;
  responsiblePerson: string;
  totalAmount: number;
  writeOffActGoodsList: Array<WriteOffActGoodsDTO>;
  writeOffActType: WriteOffActType;


  constructor(id: number, company: string, creation: Date, creator: string, responsiblePerson: string, totalAmount: number,
              writeOffActGoodsList: Array<string>, writeOffActType: WriteOffActType) {
    this.id = id;
    this.company = company;
    this.creation = creation;
    this.creator = creator;
    this.responsiblePerson = responsiblePerson;
    this.totalAmount = totalAmount;
    this.writeOffActGoodsList = writeOffActGoodsList;
    this.writeOffActType = writeOffActType;
  }
}
