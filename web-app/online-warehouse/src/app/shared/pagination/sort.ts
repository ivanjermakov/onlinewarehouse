import {SortDirection} from "./sort-direction";
import HttpParamsBuilder from "../http/http-params-builder";

export class Sort {
  field: string;
  direction: SortDirection;


  constructor(field: string, direction = SortDirection.ASC) {
    this.field = field;
    this.direction = direction;
  }

  toUrlParameters(paramsBuilder: HttpParamsBuilder): HttpParamsBuilder {
    return paramsBuilder.set('sort', `${this.field},${this.direction}`);
  }

  toNewSort(newField: string): Sort {
    return this.field == newField ? new Sort(this.field, this.inverseDirection(this.direction)) : new Sort(newField);
  }

  private inverseDirection(direction: SortDirection): SortDirection {
    return SortDirection.ASC == direction ? SortDirection.DESC : SortDirection.ASC;
  }
}
