import {CompanyDto} from '../../company/dto/company.dto';
import {WriteOffActType} from '../enum/write-off-act-type.enum';
import {WriteOffActGoodsDto} from './write-off-act-goods.dto';

export class WriteOffActDto {
  id: number;
  company: CompanyDto;
  creation: Date;
  // TODO: use UserDto
  creator: string;
  responsiblePerson: string;
  totalAmount: number;
  writeOffActGoodsList: Array<WriteOffActGoodsDto>;
  writeOffActType: WriteOffActType;


  constructor(id: number, company: CompanyDto, creation: Date, creator: string, responsiblePerson: string, totalAmount: number,
              writeOffActGoodsList: Array<WriteOffActGoodsDto>, writeOffActType: WriteOffActType) {
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
