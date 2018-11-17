import {Component, OnInit} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Page} from "../../shared/pagination/page";
import {Pageable} from "../../shared/pagination/pageable";
import {finalize} from "rxjs/operators";
import {WarehouseDto} from "../dto/warehouse.dto";
import {WarehouseService} from "../service/warehouse.service";
import {AuthenticationService} from "../../auth/_services";

@Component({
  selector: 'app-warehouse-list',
  templateUrl: './warehouse-list.component.html',
  styleUrls: ['./warehouse-list.component.css']
})
export class WarehouseListComponent implements OnInit {

  // @Output() warehouseSelected: EventEmitter<WarehouseDto> = new EventEmitter();

  // private displayedColumns = ["name", "placementsCount"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private page: Page<WarehouseDto>;

  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];

  private error: any;

  constructor(private warehouseService: WarehouseService,
              private auth: AuthenticationService) {
  }

  ngOnInit() {
    this.loadWarehouses();
  }

  // pageChanged(event: PageEvent) {
  //   this.page = null;
  //   this.pageable = new Pageable(event.pageIndex, event.pageSize);
  //   this.loadWarehouses();
  // }

  // onRowClicked(row) {
  //   this.warehouseSelected.emit(row);
  // }

  loadWarehouses() {
    this.loadingSubject.next(true);
    this.warehouseService.getWarehouses(this.auth.getCompanyId(), this.pageable).pipe(
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe(page => {
          this.page = page;
          console.log(this.page);
        }, (err: any) => {
          this.error = err;
        }
      );
  }

}
