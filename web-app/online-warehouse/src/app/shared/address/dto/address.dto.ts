export class AddressDto {
  country: string;
  region: string;
  locality: string;

  constructor(country: string, region: string, locality: string) {
    this.country = country;
    this.region = region;
    this.locality = locality;
  }
}
