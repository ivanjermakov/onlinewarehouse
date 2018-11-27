import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {GoodService} from "../good.service";
import {GoodsDto} from "../dto/goods.dto";
import {PageEvent} from "@angular/material";
import {Page} from "../../pagination/page";
import {Pageable} from "../../pagination/pageable";
import {BehaviorSubject, of} from "rxjs";
import {catchError, debounceTime, distinctUntilChanged, finalize, tap} from "rxjs/operators";
import {FormBuilder, FormGroup} from "@angular/forms";
import {GoodFilter} from "../dto/good-filter";

@Component({
  selector: 'app-goods-list',
  templateUrl: './goods-list.component.html',
  styleUrls: ['./goods-list.component.css']
})
export class GoodsListComponent implements OnInit {

  @Output() goodsSelected: EventEmitter<GoodsDto> = new EventEmitter();

  @Input() addButton: Boolean;
  @Input() inputGoods: GoodsDto[];

  private displayedColumns = ["name", "placementType", "measurementUnit", "cost", "weight", "labelling", "description"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];

  private page: Page<GoodsDto>;
  private goodsFilterForm: FormGroup;
  private goodsFilter: GoodFilter = new GoodFilter();

  private errors: any[];

  constructor(private goodsService: GoodService,
              private fb: FormBuilder) {
    this.goodsFilterForm = fb.group({
      "name": [''],
      "placementType": [''],
      "costFrom": [''],
      "costTo": ['']
    });
  }

  ngOnInit(): void {
    console.log(this.inputGoods);
    this.loadGoods();
    this.goodsFilterForm.valueChanges.pipe(debounceTime(500),
      distinctUntilChanged(),
      tap(() => {
        this.page = null;
        this.pageable.page = 0;
        let value = this.goodsFilterForm.value;
        Object.assign(this.goodsFilter, value);
        this.loadGoods();
      })
    ).subscribe();
  }

  pageChanged(event: PageEvent) {
    this.page = null;
    this.pageable = new Pageable(event.pageIndex, event.pageSize);
    this.loadGoods();

  }

  onRowClicked(goods: GoodsDto) {
    this.goodsSelected.emit(goods);
  }

  private loadGoods() {
    if (this.inputGoods) {
      this.getFromData();
    } else {
      this.loadingSubject.next(true);
      this.goodsService.getAllGoods(this.goodsFilter.toServerFilter(), this.pageable).pipe(
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

  private getFromData() {
    let filteredDataArr = this.inputGoods.filter((goods) => {
      if (this.checkField(this.goodsFilter.name)) {
        if (!goods.name.includes(this.goodsFilter.name)) {
          return false;
        }
      }
      if (this.checkField(this.goodsFilter.placementType)) {
        if (goods.placementType !== this.goodsFilter.placementType) {
          return false;
        }
      }
      if (this.checkField(this.goodsFilter.costFrom)) {
        if (goods.cost <= this.goodsFilter.costFrom) {
          return false;
        }
      }
      if (this.checkField(this.goodsFilter.costTo)) {
        if (goods.cost >= this.goodsFilter.costTo) {
          return false;
        }
      }
      return true;
    });
    let page = new Page<GoodsDto>();
    page.totalElements = filteredDataArr.length;
    page.size = this.pageable.size;
    page.number = this.pageable.page;
    page.content = filteredDataArr.slice(this.pageable.page * this.pageable.size, (this.pageable.page + 1) * this.pageable.size);
    this.page = page;


  }


  private checkField(data: any): boolean {
    return data !== null && data !== undefined && data !== '';
  }
}
