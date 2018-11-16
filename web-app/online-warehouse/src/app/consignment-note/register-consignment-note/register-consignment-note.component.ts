import { Component, OnInit } from '@angular/core';
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

@Component({
  selector: 'app-register-consignment-note',
  templateUrl: './register-consignment-note.component.html',
  styleUrls: ['./register-consignment-note.component.css']
})
export class RegisterConsignmentNoteComponent implements OnInit {
  consignmentNoteForm: FormGroup;
  counterparty: CounterpartyDto;
  carrier:  CarrierDto;
  goodsDtoList: Array<GoodsDto> = [];

  constructor(private consignmentNoteService: ConsignmentNoteService,
              private fb: FormBuilder,
              private dialog: MatDialog) {
    this.consignmentNoteForm = fb.group({
      "number": [''],
      "shipment": [''],
      "counterparty": ['', Validators.required],
      "carrier": ['', Validators.required],
      "vehicleNumber": [''],
      "consignmentNoteGoodsList": fb.array([], Validators.required),
      "consignmentNoteType": [''],
      "description": ['']
    });
  }

  ngOnInit() {
  }

  addCounterparty(counterparty: CounterpartyDto): void {
    this.counterparty = counterparty;
    this.consignmentNoteForm.patchValue({"counterparty" : counterparty});
  }

  deleteCounterparty(): void {
    this.counterparty = null;
    this.consignmentNoteForm.patchValue({"counterparty" : ''});
  }

  counterpartyModal(): void {
    const dialogRef = this.dialog.open(CounterpartyListDialogComponent, {
      disableClose: false,
      autoFocus: true,
      data: {
        counterpartyType: CounterpartyTypeEnum.CONSIGNEE,
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
    this.consignmentNoteForm.patchValue({'carrier': this.carrier});
  }

  deleteCarrier(): void {
    this.carrier = null;
    this.consignmentNoteForm.patchValue({'carrier': ''});
  }

  setDriverInfo(driverInfo: string): void {
    this.carrier.driverInfo = [];
    this.carrier.driverInfo.push(driverInfo);
    this.consignmentNoteForm.patchValue({'carrier': this.carrier});
  }

  carrierModal(): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(CarrierListDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.addCarrier(data);
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

  goodsModal(): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;

    const dialogRef = this.dialog.open(GoodsListDialogComponent, dialogConfig);

    dialogRef.afterClosed().subscribe(
      data => {
        if (data) {
          this.addGoods(data);
        }
      }
    );
  }

  onSubmit(consignmentNoteForm: FormGroup): void {
    console.log(consignmentNoteForm.value);
    this.consignmentNoteService.saveConsignmentNote(this.consignmentNoteForm.value).subscribe();
    this.clearFrom();
  }

  private clearFrom(): void {
    this.carrier = null;
    this.counterparty = null;
    this.goodsDtoList = [];
    this.consignmentNoteForm.reset();
  }
}
