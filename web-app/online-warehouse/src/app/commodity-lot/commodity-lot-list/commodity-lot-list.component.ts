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
import {BehaviorSubject, of} from "rxjs";
import {catchError, debounceTime, distinctUntilChanged, finalize, tap} from "rxjs/operators";
import {CommodityLotStatusEnum} from "../dto/enum/commodity-lot-status.enum";
import {DistributeGoodsWarehouseDialogComponent} from "../../warehouse/distribute-goods-warehouse-dialog/distribute-goods-warehouse-dialog.component";

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

  private disabled = true;

  private errors: any[];

  constructor(private commodityLotService: CommodityLotService,
              private router: Router,
              private fb: FormBuilder,
              private dialog: MatDialog) {
    this.commodityLotFilterForm = fb.group({
      "commodityLotType": [''],
      "commodityLotStatus": [''],
      "from": [''],
      "to": [''],
      "counterpartyName": [''],
    });
  }

  ngOnInit() {
    if (this.router.url === '/app/registered-commodity-lots') {
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

  onRowClicked(commodityLotListDto: CommodityLotListDto) {
    if (!this.disabled) {
      this.openModal(commodityLotListDto);
    } else {
      this.router.navigateByUrl('app/commodity-lot/' + commodityLotListDto.id);
    }
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
          });
        }
      })
    });
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
      catchError(() => of([])),
      finalize(() => this.loadingSubject.next(false))
    )
      .subscribe(page => {
        if (page instanceof Array) {
          this.errors = page as any[];
        } else {
          this.page = page;
        }
      });
  }
}
