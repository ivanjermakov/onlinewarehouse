import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {Pageable} from "../../shared/pagination/pageable";
import {Page} from "../../shared/pagination/page";
import {FormBuilder, FormGroup} from "@angular/forms";
import {GoodFilter} from "../../shared/goods/dto/good-filter";
import {debounceTime, distinctUntilChanged, tap} from "rxjs/operators";
import {MAT_DIALOG_DATA, MatDialogRef, PageEvent} from "@angular/material";
import {PlacementGoodsDto} from "../dto/placement-goods.dto";

@Component({
  selector: 'app-placement-goods-list-dialog',
  templateUrl: './placement-goods-list-dialog.component.html',
  styleUrls: ['./placement-goods-list-dialog.component.css']
})
export class PlacementGoodsListDialogComponent implements OnInit {

  @Output() goodsSelected: EventEmitter<PlacementGoodsDto> = new EventEmitter();

  placementGoods: PlacementGoodsDto[];

  private displayedColumns = ["name", "placementType", "measurementUnit", "cost", "weight", "labelling", "description", "expirationTime", "storageTime"];
  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];
  private page: Page<PlacementGoodsDto>;

  private goodsFilterForm: FormGroup;
  private goodsFilter: GoodFilter = new GoodFilter();


  constructor(private fb: FormBuilder,
              private dialogRef: MatDialogRef<PlacementGoodsListDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.goodsFilterForm = fb.group({
      "name": [''],
      "placementType": [''],
      "costFrom": [''],
      "costTo": ['']
    });

    this.placementGoods = data.placementGoods;
  }

  ngOnInit(): void {
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

  onRowClicked(goods: PlacementGoodsDto) {
    this.dialogRef.close(goods);
  }

  private loadGoods() {
    this.getFromData();
  }

  private getFromData() {
    let filteredDataArr = this.placementGoods.filter((goods) => {
      if (this.checkField(this.goodsFilter.name)) {
        if (!goods.goods.name.includes(this.goodsFilter.name)) {
          return false;
        }
      }
      if (this.checkField(this.goodsFilter.placementType)) {
        if (goods.goods.placementType !== this.goodsFilter.placementType) {
          return false;
        }
      }
      if (this.checkField(this.goodsFilter.costFrom)) {
        if (goods.goods.cost <= this.goodsFilter.costFrom) {
          return false;
        }
      }
      if (this.checkField(this.goodsFilter.costTo)) {
        if (goods.goods.cost >= this.goodsFilter.costTo) {
          return false;
        }
      }
      return true;
    });
    let page = new Page<PlacementGoodsDto>();
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
