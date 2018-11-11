import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {CommodityLotListDto} from "../dto/commodity-lot-list.dto";
import {CommodityLotService} from "../service/commodity-lot.service";
import {CommodityLotFilter} from "../dto/commodity-lot.filter";
import {CommodityLotTypeEnum} from "../dto/enum/commodity-lot-type.enum";
import {Page} from "../../shared/pagination/page";
import {Pageable} from "../../shared/pagination/pageable";
import {PageEvent} from "@angular/material";
import {FormBuilder, FormGroup} from "@angular/forms";
import {BehaviorSubject, of} from "rxjs";
import {catchError, debounceTime, distinctUntilChanged, finalize, tap} from "rxjs/operators";

@Component({
  selector: 'app-commodity-lot-list',
  templateUrl: './commodity-lot-list.component.html',
  styleUrls: ['./commodity-lot-list.component.css']
})
export class CommodityLotListComponent implements OnInit {

  commodityLotFilterForm: FormGroup;
  private displayedColumns = ["creation", "counterpartyName", "commodityLotType"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private page: Page<CommodityLotListDto>;
  private filter: CommodityLotFilter = new CommodityLotFilter();

  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];

  private errors: any[];

  constructor(private commodityLotService: CommodityLotService,
              private router: Router,
              private fb: FormBuilder) {
    this.commodityLotFilterForm = fb.group({
      "commodityLotType": [''],
      "from": [''],
      "to": [''],
      "counterpartyName": [''],
    });
  }

  ngOnInit() {
    this.loadCommodityLots();
    this.commodityLotFilterForm.valueChanges.pipe(debounceTime(250),
      distinctUntilChanged(),
      tap(() => {
        this.page = null;
        this.pageable.page = 0;
        let value = this.commodityLotFilterForm.value;
        Object.assign(this.filter, value);
        this.loadCommodityLots();
      })
    )
      .subscribe();
  }

  onRowClicked(commodityLotListDto: CommodityLotListDto) {
    this.router.navigate(['commodity-lot/' + commodityLotListDto.id]);
  }

  pageChanged(event: PageEvent) {
    this.page = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.loadCommodityLots();

  }

  getCommodityLotTypes(): Array<string> {
    return Object.keys(CommodityLotTypeEnum);
  }

  private loadCommodityLots() {
    this.loadingSubject.next(true);
    this.commodityLotService.getCommodityLots(this.filter, this.pageable, 2).pipe(
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
