import {Sort} from "./sort";
import HttpParamsBuilder from "../http/http-params-builder";

export class Pageable {
  page: number;
  size: number;
  sort: Sort;

  constructor(page: number, size?: number, sort?: Sort) {
    this.page = page;
    this.size = size;
    this.sort = sort;
  }

  toUrlParameters(paramsBuilder: HttpParamsBuilder): HttpParamsBuilder {
    paramsBuilder.addIfNotEmpty('page', this.page);
    paramsBuilder.addIfNotEmpty('size', this.size);
    if (this.sort) {
      return this.sort.toUrlParameters(paramsBuilder);
    } else {
      return paramsBuilder;
    }
  }

  // server paging starts from 0 from the first page
  toServerPageable(): Pageable {
    return new Pageable(this.page - 1, this.size, this.sort);
  }

  updateSort(sortField: string) {
    this.sort = this.sort.toNewSort(sortField);
  }
}
