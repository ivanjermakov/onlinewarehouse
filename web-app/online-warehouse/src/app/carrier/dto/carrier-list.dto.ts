class CarrierListDto {
  id: number;
  name: string;
  taxNumber: string;
  carrierType: string;
  trusted: boolean;

  constructor(id: number, name: string, taxNumber: string, carrierType: string, trusted: boolean) {
    this.id = id;
    this.name = name;
    this.taxNumber = taxNumber;
    this.carrierType = carrierType;
    this.trusted = trusted;
  }
}
