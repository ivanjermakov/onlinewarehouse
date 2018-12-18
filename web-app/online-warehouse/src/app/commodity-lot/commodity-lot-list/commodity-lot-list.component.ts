import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {CommodityLotListDto} from "../dto/commodity-lot-list.dto";
import {CommodityLotService} from "../service/commodity-lot.service";
import {CommodityLotFilter} from "../dto/commodity-lot.filter";
import {CommodityLotTypeEnum} from "../dto/enum/commodity-lot-type.enum";
import {Page} from "../../shared/pagination/page";
import {Pageable} from "../../shared/pagination/pageable";
import {MatDialog, PageEvent} from "@angular/material";
import {FormBuilder, FormGroup} from "@angular/forms";
import {BehaviorSubject} from "rxjs";
import {debounceTime, distinctUntilChanged, finalize, tap} from "rxjs/operators";
import {CommodityLotStatusEnum} from "../dto/enum/commodity-lot-status.enum";
import {DistributeGoodsWarehouseDialogComponent} from "../../warehouse/distribute-goods-warehouse-dialog/distribute-goods-warehouse-dialog.component";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Component({
  selector: 'app-commodity-lot-list',
  templateUrl: './commodity-lot-list.component.html',
  styleUrls: ['./commodity-lot-list.component.css']
})
export class CommodityLotListComponent implements OnInit {

  commodityLotFilterForm: FormGroup;
  private displayedColumns = ["creation", "counterpartyName", "commodityLotType", "commodityLotStatus"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private page: Page<CommodityLotListDto>;
  private filter: CommodityLotFilter = new CommodityLotFilter();

  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];

  private commodityLotTypeEnum = CommodityLotTypeEnum;
  private commodityLotStatusEnum = CommodityLotStatusEnum;

  private disabled = true;

  private minDate: Date = new Date(2000, 0, 1);
  private today: Date = new Date();


  constructor(private commodityLotService: CommodityLotService,
              private router: Router,
              private fb: FormBuilder,
              private dialog: MatDialog,
              private errorToast: RequestErrorToastHandlerService) {
    this.commodityLotFilterForm = fb.group({
      "commodityLotType": [''],
      "commodityLotStatus": [''],
      "from": [''],
      "to": [''],
      "counterpartyName": [''],
    });
  }

  ngOnInit() {
    if (this.router.url.includes('/app/dispatcher-commodity-lots')) {
      this.commodityLotFilterForm.patchValue({'commodityLotStatus': 'NOT_PROCESSED'});
      this.commodityLotFilterForm.patchValue({'commodityLotType': 'OUT'});
      this.displayedColumns.push('commodityLotProcessOut');
      this.disabled = false;
    }
    if (this.router.url.includes('/app/manager-commodity-lots')) {
      this.commodityLotFilterForm.patchValue({'commodityLotStatus': 'NOT_PROCESSED'});
      this.commodityLotFilterForm.patchValue({'commodityLotType': 'IN'});
      this.displayedColumns.push('commodityLotProcess');
      this.disabled = false;
    }
    this.loadCommodityLots();
    this.commodityLotFilterForm.valueChanges.pipe(debounceTime(250),
      distinctUntilChanged(),
      tap(() => {
        this.page = null;
        this.pageable.page = 0;
        // let value = this.commodityLotFilterForm.value;
        // Object.assign(this.filter, value);
        this.loadCommodityLots();
      })
    )
      .subscribe();
  }

  onProcessClicked(i: number) {
    if (this.router.url === '/app/dispatcher-commodity-lots') {
      this.commodityLotService.setCommodityLotProcessed(i).subscribe(() => {
        this.errorToast.handleSuccess('Commodity lot status updated successfully', 'Updated successfully');
        this.loadCommodityLots();
      }, (err: any) => {
        this.errorToast.handleError(err);
      });
    }
    if (this.router.url === '/app/manager-commodity-lots') {
      this.openModal(this.page.content[i]);
    }
  }

  onRowClicked(commodityLotListDto: CommodityLotListDto) {
    this.router.navigateByUrl('app/commodity-lot/' + commodityLotListDto.id);
  }

  openModal(commodityLotListDto: CommodityLotListDto): void {
    this.commodityLotService.getCommodityLot(commodityLotListDto.id).subscribe((commodityLotDto) => {
        const dialogRef = this.dialog.open(DistributeGoodsWarehouseDialogComponent, {
          disableClose: false,
          autoFocus: true,
          data: {
            commodityLot: commodityLotDto
          }
        });

        dialogRef.afterClosed().subscribe((b) => {
          if (b) {
            this.commodityLotService.setCommodityLotProcessed(commodityLotDto.id).subscribe(() => {
                this.loadCommodityLots();
              }, (err: any) => {
                this.errorToast.handleError(err);
              }
            );
          }
        })
      }, (err: any) => {
        this.errorToast.handleError(err);
      }
    );
  }

  pageChanged(event: PageEvent) {
    this.page = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.loadCommodityLots();

  }

  getCommodityLotTypes(): Array<string> {
    return Object.keys(CommodityLotTypeEnum);
  }

  getCommodityLotStatuses(): Array<string> {
    return Object.keys(CommodityLotStatusEnum);
  }

  private loadCommodityLots() {
    this.loadingSubject.next(true);
    this.commodityLotService.getCommodityLots(this.commodityLotFilterForm.value, this.pageable).pipe(
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
