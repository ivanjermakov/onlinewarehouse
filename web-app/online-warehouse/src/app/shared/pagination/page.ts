export class Page<T> {
  content: T[];
  totalPages: number;
  last: boolean;
  totalElements: number;
  size: number;
  number: number;
  sort: any;
  first: boolean;
  numberOfElements: number;

  // constructor(content: T[], totalPages: number, last: boolean, totalElements: number, size: number, number: number, sort: any, first: boolean, numberOfElements: number) {
  //   this.content = content;
  //   this.totalPages = totalPages;
  //   this.last = last;
  //   this.totalElements = totalElements;
  //   this.size = size;
  //   this.number = number;
  //   this.sort = sort;
  //   this.first = first;
  //   this.numberOfElements = numberOfElements;
  // }
}
