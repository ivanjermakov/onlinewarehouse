export class CarrierFilter {
  name: string;
  taxNumber: string;
  carrierType: string;

  constructor(name: string, taxNumber: string, carrierType: string) {
    this.name = name;
    this.taxNumber = taxNumber;
    this.carrierType = carrierType;
  }
}
