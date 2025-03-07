import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {BehaviorSubject} from "rxjs";
import {Page} from "../../shared/pagination/page";
import {FormBuilder, FormGroup} from "@angular/forms";
import {Pageable} from "../../shared/pagination/pageable";
import {debounceTime, distinctUntilChanged, finalize, tap} from "rxjs/operators";
import {MatDialog, PageEvent} from "@angular/material";
import {CounterpartyDto} from "../dto/counterparty.dto";
import {CounterpartyFilter} from "../dto/counterparty.filter";
import {CounterpartyService} from "../service/counterparty.service";
import {CounterpartyTypeEnum} from "../dto/enum/counterparty-type.enum";
import {CreateCounterpartyDialogComponent} from "../create-counterparty-dialog/create-counterparty-dialog.component";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Component({
  selector: 'app-counterparty-list',
  templateUrl: './counterparty-list.component.html',
  styleUrls: ['./counterparty-list.component.css']
})
export class CounterpartyListComponent implements OnInit {

  @Output() counterpartySelected: EventEmitter<CounterpartyDto> = new EventEmitter();

  @Input() counterpartyType: CounterpartyTypeEnum;
  @Input() addButton: Boolean;
  @Input() showCounterpartyTypeFilter: Boolean;


  private displayedColumns = ["id", "name", "taxNumber", "address"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private page: Page<CounterpartyDto>;

  private counterpartyFilterForm: FormGroup;
  private filter: CounterpartyFilter = new CounterpartyFilter();

  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];

  constructor(private counterpartyService: CounterpartyService,
              private fb: FormBuilder,
              private dialog: MatDialog,
              private errorToast: RequestErrorToastHandlerService) {
    this.counterpartyFilterForm = fb.group({
      "name": [''],
      "taxNumber": [''],
      "counterpartyType": ['']
    });
  }

  ngOnInit() {
    if (this.counterpartyType) {
      this.filter.counterpartyType = this.counterpartyType;
    }
    this.loadCounterparties();
    this.counterpartyFilterForm.valueChanges.pipe(debounceTime(250),
      distinctUntilChanged(),
      tap(() => {
        this.page = null;
        this.pageable.page = 0;
        let value = this.counterpartyFilterForm.value;
        Object.assign(this.filter, value);
        this.loadCounterparties();
      })
    )
      .subscribe();
  }

  pageChanged(event: PageEvent): void {
    this.page = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.loadCounterparties();
  }

  onRowClicked(row): void {
    this.counterpartySelected.emit(row);
  }

  getCounterpartyTypes(): Array<string> {
    return Object.keys(CounterpartyTypeEnum);
  }

  addCounterpartyModal(): void {
    const dialogRef = this.dialog.open(CreateCounterpartyDialogComponent, {
      disableClose: false,
      autoFocus: true,
      data: {
        counterpartyType: this.counterpartyType
      }
    });

    dialogRef.afterClosed().subscribe(() => {
        this.loadCounterparties();
      }
    );
  }

  loadCounterparties(): void {
    this.loadingSubject.next(true);
    this.counterpartyService.getCounterparties(this.filter, this.pageable,).pipe(
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe((page) => {
          this.page = page;
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }

}
