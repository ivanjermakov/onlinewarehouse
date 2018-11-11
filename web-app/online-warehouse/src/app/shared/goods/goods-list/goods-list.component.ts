import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {GoodService} from "../good.service";
import {GoodsDto} from "../goods.dto";
import {PageEvent} from "@angular/material";
import {Page} from "../../pagination/page";
import {Pageable} from "../../pagination/pageable";
import {BehaviorSubject, of} from "rxjs";
import {catchError, finalize} from "rxjs/operators";

@Component({
  selector: 'app-goods-list',
  templateUrl: './goods-list.component.html',
  styleUrls: ['./goods-list.component.css']
})
export class GoodsListComponent implements OnInit {

  @Output() goodsSelected: EventEmitter<GoodsDto> = new EventEmitter();

  @Input() addButton: Boolean;

  private displayedColumns = ["name", "placementType", "measurementUnit", "cost", "weight", "labelling", "description"];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private page: Page<GoodsDto>;


  private pageable: Pageable = new Pageable(0, 10);
  private pageSizeOptions: number[] = [10, 25, 50];

  private errors: any[];

  constructor(private goodsService: GoodService) {
  }

  ngOnInit() {
    this.loadGoods();
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
    this.loadingSubject.next(true);
    this.goodsService.getAllGoods(2, this.pageable).pipe(
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
