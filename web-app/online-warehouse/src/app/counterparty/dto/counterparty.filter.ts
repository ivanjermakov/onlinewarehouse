export class CounterpartyFilter {

  name: string;
  counterpartyType: string;
  taxNumber: string;


  constructor(name?: string, counterpartyType?: string, taxNumber?: string) {
    this.name = name;
    this.counterpartyType = counterpartyType;
    this.taxNumber = taxNumber;
  }
}
