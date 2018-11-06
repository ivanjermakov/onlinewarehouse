import {CarrierTypeEnum} from "./enum/carrier-type.enum";

export class CarrierDto {
  id: number;
  addressCountry: string;
  addressRegion: string;
  addressLocality: string;
  name: string;
  taxNumber: string;
  carrierType: CarrierTypeEnum;
  trusted: boolean;
  drivers: Array<string>;

  constructor(id: number, addressCountry: string, addressRegion: string, addressLocality: string, name: string, taxNumber: string, carrierType: CarrierTypeEnum, trusted: boolean, drivers: Array<string>) {
    this.id = id;
    this.addressCountry = addressCountry;
    this.addressRegion = addressRegion;
    this.addressLocality = addressLocality;
    this.name = name;
    this.taxNumber = taxNumber;
    this.carrierType = carrierType;
    this.trusted = trusted;
    this.drivers = drivers;
  }
}
