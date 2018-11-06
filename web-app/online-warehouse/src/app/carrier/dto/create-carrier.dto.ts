export class CreateCarrierDto {
  addressCountry: string;
  addressRegion: string;
  addressLocality: string;
  name: string;
  taxNumber: string;
  carrierType: string;
  trusted: boolean;


  constructor(addressCountry: string, addressRegion: string, addressLocality: string, name: string, taxNumber: string, carrierType: string, trusted: boolean) {
    this.addressCountry = addressCountry;
    this.addressRegion = addressRegion;
    this.addressLocality = addressLocality;
    this.name = name;
    this.taxNumber = taxNumber;
    this.carrierType = carrierType;
    this.trusted = trusted;
  }
}
