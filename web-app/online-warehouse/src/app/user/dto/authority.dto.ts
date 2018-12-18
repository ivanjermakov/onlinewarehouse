import {AuthorityNameEnum} from "./enum/authority-name.enum";

export class AuthorityDto {
  name: AuthorityNameEnum;

  constructor(name: AuthorityNameEnum) {
    this.name = name;
  }
}
