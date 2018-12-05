import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {PlacementGoodsDto} from "../dto/placement-goods.dto";
import {WarehouseDto} from "../dto/warehouse.dto";
import {BehaviorSubject} from "rxjs";
import {WarehouseService} from "../service/warehouse.service";
import {finalize} from "rxjs/operators";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Component({
  selector: 'app-collect-goods-warehouse',
  templateUrl: './collect-goods-warehouse.component.html',
  styleUrls: ['./collect-goods-warehouse.component.css']
})
export class CollectGoodsWarehouseComponent implements OnInit {
  @Output() goodsChanged: EventEmitter<PlacementGoodsDto[]> = new EventEmitter<PlacementGoodsDto[]>();
  @Output() warehouseChanged: EventEmitter<WarehouseDto> = new EventEmitter<WarehouseDto>();
  @Output() dataChanged: EventEmitter<any> = new EventEmitter<any>();
  @Input() warehouseId: number;

  show = true;
  resultList: PlacementGoodsDto[] = [];
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private warehouseDto: WarehouseDto;

  constructor(private warehouseService: WarehouseService,
              private errorToast: RequestErrorToastHandlerService) {
  }

  ngOnInit() {
    this.loadingSubject.next(true);
    this.warehouseService.getWarehouse(this.warehouseId)
      .pipe(
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe(warehouseDto => {
          this.warehouseDto = warehouseDto;
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }

  push(item: PlacementGoodsDto, arr: PlacementGoodsDto[], index: number) {
    if (item.amount > 0) {
      let itemExist: boolean = false;
      this.resultList.forEach((testItem) => {
        if (testItem.goods.name === item.goods.name &&
          new Date(testItem.expirationDate).getTime() === new Date(item.expirationDate).getTime() &&
          (testItem as any).arr === arr) {
          testItem.amount++;
          itemExist = true;
        }
      });
      if (!itemExist) {
        let newItem = Object.assign({arr: arr, index: index}, item);
        newItem.amount = 1;
        this.resultList.push(newItem);
      }
      console.log(item);
      this.subtract(arr, index);
    }
  }

  remove(item, index) {
    item.amount--;
    if (item.amount === 0) {
      this.resultList.splice(index, 1);
    }
    item.arr[item.index].amount++;
  }

  subtract(arr, index) {
    arr[index].amount--;
  }

  submit() {
    // this.warehouseDto.placements.forEach((placement) => {
    //   placement.placementGoodsList = placement.placementGoodsList.filter((goods) => {
    //     return goods.amount > 0;
    //   })
    // });
    let newResultList: any = this.resultList.slice();
    for (let i = 0; i < newResultList.length; i++) {
      for (let j = i + 1; j < newResultList.length;) {
        if (newResultList[i].goods.id === newResultList[j].goods.id) {
          newResultList[i].amount += newResultList[j].amount;
          newResultList.splice(j, 1);
        } else {
          j++;
        }
      }
    }
    this.goodsChanged.emit(newResultList);
    this.warehouseChanged.emit(this.warehouseDto)
  }

  change() {
    this.dataChanged.emit();
  }
}
