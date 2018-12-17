import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Pageable} from "../../shared/pagination/pageable";
import {WarehouseService} from "../service/warehouse.service";
import {WarehouseDto} from "../dto/warehouse.dto";
import {MeasurementUnitEnum} from "../../shared/enum/measurement-unit.enum";
import {PlacementTypeEnum} from "../../shared/enum/placement-type.enum";
import {CommodityLotGoodsDto} from "../../commodity-lot/dto/commodity-lot-goods.dto";
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {PlacementGoodsDto} from "../dto/placement-goods.dto";
import {PlacementDto} from "../dto/placement.dto";
import {CommodityLotService} from "../../commodity-lot/service/commodity-lot.service";
import {CommodityLotDto} from "../../commodity-lot/dto/commodity-lot.dto";
import {CdkDrag, CdkDragDrop, CdkDropList, moveItemInArray, transferArrayItem} from "@angular/cdk/drag-drop";
import {BehaviorSubject} from "rxjs";
import {finalize} from "rxjs/operators";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Component({
  selector: 'app-distribute-goods-warehouse',
  templateUrl: './distribute-goods-warehouse.component.html',
  styleUrls: ['./distribute-goods-warehouse.component.css']
})
export class DistributeGoodsWarehouseComponent implements OnInit {

  @Input() commodityLot: CommodityLotDto;
  @Output() submitted: EventEmitter<any> = new EventEmitter<any>();
  goodsCount: FormGroup;
  placementDropListArray: PlacementDropList[];
  test = false;
  testControl = new FormControl([''], [Validators.min(3), Validators.max(5)])
  private loadingSubject = new BehaviorSubject<boolean>(false);
  loading$ = this.loadingSubject.asObservable();
  private warehouses: WarehouseDto[];
  private warehouse: WarehouseDto;
  private distributeGoodsForm: FormGroup;
  private warehouseControl: FormControl;
  private warehouseId: number;
  private counterpartyId: number;

  constructor(private warehouseService: WarehouseService,
              private commodityLotService: CommodityLotService,
              private fb: FormBuilder,
              private errorToast: RequestErrorToastHandlerService) {
  }

  ngOnInit() {
    this.loadingSubject.next(true);
    this.warehouseService.getWarehouses(new Pageable(0, Number.MAX_SAFE_INTEGER))
      .pipe(
        finalize(() => this.loadingSubject.next(false))
      )
      .subscribe(page => {
          this.warehouses = page.content;
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
    this.distributeGoodsForm = this.fb.group({
      storageTime: ['', [Validators.required, Validators.min(1)]]
    });
    this.warehouseControl = this.fb.control('', Validators.required);

    this.goodsCount = this.fb.group({arr: this.fb.array([])});
    //
    this.commodityLot.commodityLotGoodsList.forEach((commodityLotGoods) => {
      (this.goodsCount.controls['arr'] as FormArray)
        .push(this.fb.control([''], [Validators.max(commodityLotGoods.amount - 1), Validators.min(1), Validators.required]))
    });

    // if (!this.commodityLot) {
    //   // this.commodityLot = new CommodityLotDto();
    //   this.commodityLotService.getCommodityLot(1)
    //     .subscribe((commodityLot) => {
    //       this.commodityLot = commodityLot;
    //       console.log('test');
    //       console.log(this.commodityLot);
    //
    //       commodityLot.commodityLotGoodsList.forEach((commodityLotGoods) => {
    //         (this.goodsCount.controls['arr'] as FormArray)
    //           .push(this.fb.control([''], [Validators.max(commodityLotGoods.amount - 1), Validators.min(1)]))
    //       });
    //       console.log(this.goodsCount.controls['arr']);
    //     });
    //
    // }

  }

  saveUpdatedWarehouse() {
    this.joinPlacementDropGroupAndWarehouse(this.placementDropListArray, this.warehouses[this.warehouseControl.value]);
    this.warehouseService.updateWarehouse(this.warehouses[this.warehouseControl.value], this.countCommodityLotProfit())
      .subscribe(() => {
          this.errorToast.handleSuccess('Commodity lot distributed successfully', 'Saved successfully');
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
    this.submitted.emit();
  }

  mapWarehouseToPlacementDropGroup(warehouseDto: WarehouseDto): PlacementDropList[] {
    this.warehouseId = warehouseDto.id;
    let placementDropGroup: PlacementDropList[] = [];
    warehouseDto.placements.forEach((placement) => {
      let placementLoad: number = 0;
      placement.placementGoodsList.forEach((placementGoods) => {
        placementLoad += placementGoods.amount;
      });
      placementDropGroup.push(new PlacementDropList(placement.id, placement.measurementUnit, placement.placementType, placement.size, placementLoad,
        placement.storageCost, []))
    });
    return placementDropGroup;
  }

  joinPlacementDropGroupAndWarehouse(placementDropGroup: PlacementDropList[], warehouse: WarehouseDto) {
    let placementDtos: PlacementDto[] = placementDropGroup.map((placementDropList) => {
      let placementGoodsDtos: PlacementGoodsDto[] = placementDropList.arr.map((arr) => {
        return new PlacementGoodsDto(null, arr.amount, arr.goods, this.commodityLot.counterpartyId,
          // 10,
          this.distributeGoodsForm.controls['storageTime'].value,
          // this.addDays(Date.now(), 10))
          this.addDays(Date.now(), this.distributeGoodsForm.controls['storageTime'].value))
      });
      return new PlacementDto(placementDropList.id, placementGoodsDtos);
    });

    placementDtos.forEach((placement) => {
      for (let i = 0; i < warehouse.placements.length; i++) {
        if (placement.id === warehouse.placements[i].id) {
          placement.placementGoodsList.forEach((goods) => {
            if (warehouse.placements[i].placementGoodsList.length === 0) {
              warehouse.placements[i].placementGoodsList.push(goods)
            } else {
              for (let j = 0; j < warehouse.placements[i].placementGoodsList.length; j++) {
                if (this.placementGoodsEquals(goods, warehouse.placements[i].placementGoodsList[j])) {
                  warehouse.placements[i].placementGoodsList[j].amount += goods.amount;
                  break;
                }
                if ((j + 1) === warehouse.placements[i].placementGoodsList.length) {
                  warehouse.placements[i].placementGoodsList.push(goods);
                  j++;
                }
              }
            }
          })
        }
      }
    })
  }

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.previousIndex);
    } else {
      (this.goodsCount.controls['arr'] as FormArray).removeAt(event.previousIndex);
      transferArrayItem(event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex);
    }
  }

  specialUseCase(drag?: CdkDrag, drop?: CdkDropList) {
    let dragArr = drag.element.nativeElement.id.split('_');
    let dropArr = drop.id.split('_');
    return (dragArr[1] === dropArr[1] && //placementType equals
      dragArr[2] === dropArr[2] && //measurementUnit equals
      parseInt(dragArr[3]) <= parseInt(dropArr[3]) - parseInt(dropArr[4]) // amount <= size - current load
    );
  }

  selectionChange() {
    if (true) {
      this.placementDropListArray = this.mapWarehouseToPlacementDropGroup(this.warehouses[this.warehouseControl.value]);
    }
  }

  divide(item, index: number) {
    let formValue: number = (this.goodsCount.controls['arr'] as FormArray).at(index).value;
    (this.goodsCount.controls['arr'] as FormArray).at(index).reset();
    this.commodityLot.commodityLotGoodsList[index].amount -= formValue;
    let newCommodityLotGoods: CommodityLotGoodsDto =
      Object.assign({}, this.commodityLot.commodityLotGoodsList[index]) as CommodityLotGoodsDto;
    newCommodityLotGoods.amount = formValue;
    (this.goodsCount.controls['arr'] as FormArray).at(index)
      .setValidators(Validators.max(this.commodityLot.commodityLotGoodsList[index].amount - 1));
    this.commodityLot.commodityLotGoodsList.splice(index, 0, newCommodityLotGoods);
    (this.goodsCount.controls['arr'] as FormArray)
      .insert(index, this.fb.control([''], [Validators.max(formValue - 1), Validators.min(1), Validators.required]));
  }

  dateEquals(d1: Date, d2: Date) {
    let date1, date2;
    if (d1 instanceof Date) {
      date1 = d1.toISOString().split('T')[0];
    } else {
      date1 = d1;
    }
    if (d2 instanceof Date) {
      date2 = d2.toISOString().split('T')[0];
    } else {
      date2 = d2;
    }
    return date1 === date2;
  }

  addDays(date: number, days: number): Date {
    let result = new Date(date);
    // result.setHours(0, 0, 0, 0);
    result.setDate(result.getDate() + days);
    return result; //.toISOString().split('T')[0];
  }

  countAdded(arr: Array<CommodityLotGoodsDto>): number {
    let result: number = 0;
    arr.forEach((goods) => {
      result += goods.amount;
    });
    return result;
  }

  private placementGoodsEquals(p1: PlacementGoodsDto, p2: PlacementGoodsDto) {
    let b = (p1.counterpartyId === p2.counterpartyId &&
      p1.goods.id === p2.goods.id &&
      this.dateEquals(p1.expirationDate, p2.expirationDate) &&
      p1.storageTimeDays == p2.storageTimeDays);
    return b;
  }

  private countCommodityLotProfit(): Array<CommodityLotProfit> {
    let commodityLotProfitList: Array<CommodityLotProfit> = [];
    this.placementDropListArray.forEach((placementDropListValue) => {
      let amount: number = 0;
      placementDropListValue.arr.forEach((commodityLotGoodsDto) =>
        amount = +commodityLotGoodsDto.amount * placementDropListValue.storageCost * this.distributeGoodsForm.controls['storageTime'].value
      );
      if (amount > 0) {
        commodityLotProfitList.push(new CommodityLotProfit(
          placementDropListValue.id,
          new Date(),
          amount
        ))
      }
    });
    return commodityLotProfitList;
  }
}

class PlacementDropList {
  id: number;
  measurementUnit: MeasurementUnitEnum;
  placementType: PlacementTypeEnum;
  size: number;
  placementLoad: number;
  storageCost: number;
  arr: Array<CommodityLotGoodsDto>;


  constructor(id: number, measurementUnit: MeasurementUnitEnum, placementType: PlacementTypeEnum, size: number, placementLoad: number, storageCost: number, arr: Array<CommodityLotGoodsDto>) {
    this.id = id;
    this.measurementUnit = measurementUnit;
    this.placementType = placementType;
    this.size = size;
    this.placementLoad = placementLoad;
    this.storageCost = storageCost;
    this.arr = arr;
  }
}

export class CommodityLotProfit {
  placementId: number;
  creation: Date;
  amount: number;

  constructor(placementId: number, creation: Date, amount: number) {
    this.placementId = placementId;
    this.creation = creation;
    this.amount = amount;
  }
}
