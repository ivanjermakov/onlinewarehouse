import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {debounceTime, distinctUntilChanged, finalize, tap} from "rxjs/operators";
import {CarrierService} from "../service/carrier.service";
import {CarrierFilter} from "../dto/carrier.filter";
import {CarrierListDto} from "../dto/carrier-list.dto";
import {Page} from "../../shared/pagination/page";
import {Pageable} from "../../shared/pagination/pageable";
import {FormBuilder, FormGroup} from "@angular/forms";
import {CarrierTypeEnum} from "../dto/enum/carrier-type.enum";
import {MatDialog, PageEvent} from "@angular/material";
import {CreateCarrierDialogComponent} from "../create-carrier-dialog/create-carrier-dialog.component";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Component({
  selector: 'app-carrier-list',
  templateUrl: './carrier-list.component.html',
  styleUrls: ['./carrier-list.component.css']
})
export class CarrierListComponent implements OnInit {

  @Output() carrierSelected: EventEmitter<CarrierListDto> = new EventEmitter();

  @Input() addButton: Boolean;

  private displayedColumns = ["id", "name", "taxNumber", "carrierType", "trusted"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private page: Page<CarrierListDto>;

  private carrierFilterForm: FormGroup;
  private filter: CarrierFilter = new CarrierFilter('', '', '');

  private carrierTypeEnum = CarrierTypeEnum;

  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];

  private error: any;

  constructor(private carrierService: CarrierService,
              private fb: FormBuilder,
              private dialog: MatDialog,
              private errorToast: RequestErrorToastHandlerService) {
    this.carrierFilterForm = fb.group({
      "name": [''],
      "taxNumber": [''],
      "carrierType": ['']
    });
  }

  ngOnInit() {
    this.loadCarriers();
    this.carrierFilterForm.valueChanges.pipe(debounceTime(250),
      distinctUntilChanged(),
      tap(() => {
        this.page = null;
        this.pageable.page = 0;
        let value = this.carrierFilterForm.value;
        Object.assign(this.filter, value);
        this.loadCarriers();
      })
    )
      .subscribe();
  }

  pageChanged(event: PageEvent) {
    this.page = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.loadCarriers();
  }

  onRowClicked(row) {
    this.carrierSelected.emit(row);
  }

  getCarrierTypes(): Array<string> {
    return Object.keys(CarrierTypeEnum);
  }

  addCarrierModal(): void {
    const dialogRef = this.dialog.open(CreateCarrierDialogComponent, {
      disableClose: false,
      autoFocus: true,
    });

    dialogRef.afterClosed().subscribe(() => {
        this.loadCarriers();
      }
    );
  }

  loadCarriers() {
    this.loadingSubject.next(true);
    this.carrierService.getCarriers(this.filter, this.pageable).pipe(
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe((page) => {
          this.page = page;
        }, (err: any) => {
          this.error = err;
          this.errorToast.handleError(err)
        }
      );
  }
}
