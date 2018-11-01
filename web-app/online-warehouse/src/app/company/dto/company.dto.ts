class CompanyDto {
  id: number;
  name: string;
  sizeType: string;
  change: Date;
  actionType: string;

  constructor(id: number, name: string, sizeType: string, change: Date, actionType: string) {
    this.id = id;
    this.name = name;
    this.sizeType = sizeType;
    this.change = change;
    this.actionType = actionType;
  }
}
