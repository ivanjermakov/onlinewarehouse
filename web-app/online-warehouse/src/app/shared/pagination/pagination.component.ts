import {Component, EventEmitter, Input, Output} from "@angular/core";
import {Page} from "./page";
import {Pageable} from "./pageable";

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
})
export class PaginationComponent {
  @Input() page: Page<any>;
  @Input() pageable: Pageable;
  @Output() onGetData = new EventEmitter<string>();

  getData(newPage) {
    if (this.page.number + 1 !== newPage) {
      this.onGetData.emit();
    }
  }
}
