class CarrierDto {
  id: number;
  addressCountry: string;
  addressRegion: string;
  addressLocality: string;
  name: string;
  taxNumber: string;
  carrierType: string;
  trusted: boolean;
  drivers: Array<string>;
}
