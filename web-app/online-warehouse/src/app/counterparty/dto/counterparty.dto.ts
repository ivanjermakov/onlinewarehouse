import {CompanyDto} from '../../company/dto/company.dto';
import {CounterpartyType} from '../enum/counterparty-type.enum';

export class CounterpartyDto {
  id: number;
  // TODO: replace with AddressDto
  address: string;
  company: CompanyDto;
  name: string;
  counterpartyType: CounterpartyType;
  taxNumber: string;


  constructor(id: number, address: string, company: CompanyDto, name: string, counterpartyType: CounterpartyType, taxNumber: string) {
    this.id = id;
    this.address = address;
    this.company = company;
    this.name = name;
    this.counterpartyType = counterpartyType;
    this.taxNumber = taxNumber;
  }
}
