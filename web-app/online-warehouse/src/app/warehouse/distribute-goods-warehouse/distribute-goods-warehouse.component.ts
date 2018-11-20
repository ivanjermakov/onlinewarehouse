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
import {CdkDragDrop, moveItemInArray, transferArrayItem} from "@angular/cdk/drag-drop";
import {BehaviorSubject} from "rxjs";
import {finalize} from "rxjs/operators";

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
  error: any;
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
              private fb: FormBuilder) {
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
          this.error = err;
        }
      );
    this.distributeGoodsForm = this.fb.group({
      storageTime: ['', Validators.required]
    });
    this.warehouseControl = this.fb.control('', Validators.required);

    this.goodsCount = this.fb.group({arr: this.fb.array([])});
    //
    this.commodityLot.commodityLotGoodsList.forEach((commodityLotGoods) => {
      (this.goodsCount.controls['arr'] as FormArray)
        .push(this.fb.control([''], [Validators.max(commodityLotGoods.amount - 1), Validators.min(1)]))
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
    this.warehouseService.updateWarehouse(this.warehouses[this.warehouseControl.value]).subscribe();
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

  selectionChange() {
    if (true) {
      this.placementDropListArray = this.mapWarehouseToPlacementDropGroup(this.warehouses[this.warehouseControl.value]);
    }
  }

  divide(item, index: number) {
    let formValue: number = (this.goodsCount.controls['arr'] as FormArray).at(index).value;
    this.commodityLot.commodityLotGoodsList[index].amount -= formValue;
    let newCommodityLotGoods: CommodityLotGoodsDto =
      Object.assign({}, this.commodityLot.commodityLotGoodsList[index]) as CommodityLotGoodsDto;
    newCommodityLotGoods.amount = formValue;
    this.commodityLot.commodityLotGoodsList.splice(index, 0, newCommodityLotGoods);
    (this.goodsCount.controls['arr'] as FormArray)
      .insert(index, this.fb.control([''], [Validators.max(formValue - 1), Validators.min(1)]))
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

  private placementGoodsEquals(p1: PlacementGoodsDto, p2: PlacementGoodsDto) {
    let b = (p1.counterpartyId === p2.counterpartyId &&
      p1.goods === p2.goods &&
      this.dateEquals(p1.expirationDate, p2.expirationDate) &&
      p1.storageTimeDays == p2.storageTimeDays);
    return b;
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
