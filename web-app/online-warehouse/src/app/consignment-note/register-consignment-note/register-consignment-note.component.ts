import {Component, Input, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {GoodsListDialogComponent} from "../../shared/goods/goods-list-dialog/goods-list-dialog.component";
import {GoodsDto} from "../../shared/goods/dto/goods.dto";
import {MatDialog, MatDialogConfig} from "@angular/material";
import {CarrierDto} from "../../carrier/dto/carrier.dto";
import {CarrierListDialogComponent} from "../../carrier/carrier-list-dialog/carrier-list-dialog.component";
import {CounterpartyListDialogComponent} from "../../counterparty/counterparty-list-dialog/counterparty-list-dialog.component";
import {CounterpartyTypeEnum} from "../../counterparty/dto/enum/counterparty-type.enum";
import {CounterpartyDto} from "../../counterparty/dto/counterparty.dto";
import {ConsignmentNoteService} from "../consignment-note.service";
import {DriverDto} from "../../carrier/driver/driver.dto";
import {DriverListDialogComponent} from "../../carrier/driver/driver-list-dialog/driver-list-dialog.component";
import {ActivatedRoute, Router} from "@angular/router";
import {ConsignmentNoteType} from "../dto/enum/consignment-note-type.enum";
import {ConsignmentNoteDto} from "../dto/consignment-note-dto";
import {UpdateConsignmentNoteDto} from "../dto/update-consignment-note-dto";
import {WarehouseDto} from "../../warehouse/dto/warehouse.dto";
import {WarehouseService} from "../../warehouse/service/warehouse.service";
import {PlacementGoodsDto} from "../../warehouse/dto/placement-goods.dto";

@Component({
  selector: 'app-register-consignment-note',
  templateUrl: './register-consignment-note.component.html',
  styleUrls: ['./register-consignment-note.component.css']
})
export class RegisterConsignmentNoteComponent implements OnInit {
  // remember
  @Input() inputWarehouseId: number;
  @Input() inputConsignmentNoteType: ConsignmentNoteType;
  private warehouseDto: WarehouseDto;
  //
  private cnType = ConsignmentNoteType;
  private carrierType = '';
  private consignmentNoteForm: FormGroup;
  private counterparty: CounterpartyDto;
  private carrier: CarrierDto;
  private driver: DriverDto;
  private goodsDtoList: Array<GoodsDto> = [];
  private isCreate = true;
  private updateConsignmentNote: UpdateConsignmentNoteDto = new UpdateConsignmentNoteDto();

  constructor(private consignmentNoteService: ConsignmentNoteService,
              private warehouseService: WarehouseService,
              private fb: FormBuilder,
              private route: ActivatedRoute,
              private dialog: MatDialog,
              private router: Router) {
    this.consignmentNoteForm = fb.group({
      "number": [''],
      "shipment": [''],
      "counterparty": ['', Validators.required],
      "carrier": ['', Validators.required],
      "driver": ['', Validators.required],
      "vehicleNumber": [''],
      "consignmentNoteGoodsList": fb.array([], Validators.required),
      "consignmentNoteType": [''],
      "description": ['']
    });
  }

  ngOnInit(): void {
    if (this.inputConsignmentNoteType) {
      this.consignmentNoteForm.controls["consignmentNoteType"].patchValue(this.inputConsignmentNoteType);
      this.consignmentNoteForm.controls["shipment"].patchValue(new Date());
    } else {
      this.getConsignmentNote();
    }
  }

  getConsignmentNote(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!Number.isNaN(id) && id != 0) {
      this.consignmentNoteService.getConsignmentNote(id)
        .subscribe((consignmentNote) => {
          this.updateConsignmentNote.id = consignmentNote.id;
          this.setData(consignmentNote);
          this.isCreate = false;
        });
    }
  }

  setData(consignmentNote: ConsignmentNoteDto): void {
    this.carrierType = consignmentNote.consignmentNoteType;
    this.counterparty = consignmentNote.counterparty;
    this.carrier = consignmentNote.carrier;
    this.carrierType = consignmentNote.carrier.carrierType;
    this.driver = consignmentNote.driver;
    this.consignmentNoteForm.patchValue({"number": consignmentNote.number});
    this.consignmentNoteForm.patchValue({"shipment": consignmentNote.shipment});
    this.consignmentNoteForm.patchValue({"counterparty": consignmentNote.counterparty});
    this.consignmentNoteForm.patchValue({"carrier": consignmentNote.carrier});
    this.consignmentNoteForm.patchValue({"driver": consignmentNote.driver});
    this.consignmentNoteForm.patchValue({"vehicleNumber": consignmentNote.vehicleNumber});
    this.consignmentNoteForm.patchValue({"consignmentNoteType": consignmentNote.consignmentNoteType});
    this.consignmentNoteForm.patchValue({"description": consignmentNote.description});
    consignmentNote.consignmentNoteGoodsList.forEach(item => {
      this.goodsDtoList.push(item.goods);
      (this.consignmentNoteForm.controls['consignmentNoteGoodsList'] as FormArray).push(this.fb.group({
        "goods": item.goods,
        "amount": item.amount
      }));
    });
  }

  getTypes(): Array<string> {
    return Object.keys(ConsignmentNoteType);
  }

  addCounterparty(counterparty: CounterpartyDto): void {
    this.counterparty = counterparty;
    this.consignmentNoteForm.patchValue({"counterparty": counterparty});
  }

  deleteCounterparty(): void {
    this.counterparty = null;
    this.consignmentNoteForm.patchValue({"counterparty": ''});
  }

  counterpartyModal(): void {
    const dialogRef = this.dialog.open(CounterpartyListDialogComponent, {
      disableClose: false,
      autoFocus: true,
      data: {
        counterpartyType: this.inputConsignmentNoteType ? CounterpartyTypeEnum.CONSIGNOR : CounterpartyTypeEnum.CONSIGNEE,
        showCounterpartyTypeFilter: false,
        addButton: true
      }
    });

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.addCounterparty(data);
        }
      }
    );
  }

  addCarrier(carrier: CarrierDto): void {
    this.carrier = carrier;
    this.carrierType = carrier.carrierType;
    this.consignmentNoteForm.patchValue({'carrier': this.carrier});
  }

  deleteCarrier(): void {
    this.carrier = null;
    this.carrierType = '';
    this.consignmentNoteForm.patchValue({'carrier': ''});
  }

  carrierModal(): void {
    const dialogRef = this.dialog.open(CarrierListDialogComponent, {
      disableClose: false,
      autoFocus: true,
      data: {
        addButton: true
      }
    });

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.addCarrier(data);
        }
      }
    );
  }

  addDriver(driver: DriverDto): void {
    this.driver = driver;
    this.consignmentNoteForm.patchValue({'driver': this.driver});
  }

  deleteDriver(): void {
    this.driver = null;
    this.consignmentNoteForm.patchValue({'driver': ''});
  }

  driverModal(): void {
    const dialogRef = this.dialog.open(DriverListDialogComponent, {
      disableClose: false,
      autoFocus: true,
      data: {
        carrierId: this.carrier.id
      }
    });

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.addDriver(data);
        }
      }
    );
  }

  addGoods(goods: GoodsDto): void {
    this.goodsDtoList.push(goods);
    let index = this.goodsDtoList.length - 1;
    (this.consignmentNoteForm.controls['consignmentNoteGoodsList'] as FormArray).push(this.fb.group({
      "goods": [this.goodsDtoList[index]],
      "amount": ['']
    }));
  }

  deleteGood(i: number): void {
    this.goodsDtoList.splice(i, 1);
    (this.consignmentNoteForm.controls['consignmentNoteGoodsList'] as FormArray).removeAt(i);
  }

  addGoodsOutCN(placementGoodsDto: PlacementGoodsDto[]): void {
    placementGoodsDto.forEach((goods) => {
      (this.consignmentNoteForm.controls['consignmentNoteGoodsList'] as FormArray).push(this.fb.group({
        "goods": [goods.goods],
        "amount": [goods.amount]
      }));
    })
  }

  setWarehouseOutCN(warehouseDto: WarehouseDto) {
    this.warehouseDto = warehouseDto;
  }

  deleteGoodsOutCN(): void {
    this.warehouseDto = null;
    while ((this.consignmentNoteForm.controls['consignmentNoteGoodsList'] as FormArray).length > 0) {
      (this.consignmentNoteForm.controls['consignmentNoteGoodsList'] as FormArray).removeAt(0);
    }
  }

  goodsModal(): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    dialogConfig.data = {inputGoods: null};

    const dialogRef = this.dialog.open(GoodsListDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.addGoods(data);
        }
      }
    );
  }

  onSubmit(): void {
    if (this.warehouseDto) {
      this.warehouseService.updateWarehouseAndCreateConsignmentNote(this.warehouseDto, this.consignmentNoteForm.value).subscribe()
    } else {
      if (this.isCreate) {
        this.consignmentNoteService.saveConsignmentNote(this.consignmentNoteForm.value).subscribe();
      } else {
        Object.assign(this.updateConsignmentNote, this.consignmentNoteForm.value);
        this.consignmentNoteService.updateConsignmentNote(this.updateConsignmentNote).subscribe();
        this.router.navigateByUrl("app/consignment-notes/" + this.updateConsignmentNote.id);
      }
    }
    this.clearFrom();
  }

  private clearFrom(): void {
    this.carrier = null;
    this.carrierType = '';
    this.counterparty = null;
    this.driver = null;
    this.goodsDtoList = [];
    this.isCreate = true;
    this.consignmentNoteForm.reset();
  }
}
