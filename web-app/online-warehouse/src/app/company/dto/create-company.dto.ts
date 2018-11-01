class CreateCompanyDto {
  name: string;
  sizeType: string;


  constructor(name: string, sizeType: string) {
    this.name = name;
    this.sizeType = sizeType;
  }
}
