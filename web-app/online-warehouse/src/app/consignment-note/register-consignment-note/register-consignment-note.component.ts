import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {CreateConsignmentNoteDto} from "../dto/create-consignment-note-dto";
import {GoodsListDialogComponent} from "../../shared/goods/goods-list-dialog/goods-list-dialog.component";
import {GoodsDto} from "../../shared/goods/goods.dto";
import {MatDialog, MatDialogConfig} from "@angular/material";
import {CarrierDto} from "../../carrier/dto/carrier.dto";
import {CarrierListDialogComponent} from "../../carrier/carrier-list-dialog/carrier-list-dialog.component";
import {group} from "@angular/animations";
import {CounterpartyListDialogComponent} from "../../counterparty/counterparty-list-dialog/counterparty-list-dialog.component";
import {CounterpartyTypeEnum} from "../../counterparty/dto/enum/counterparty-type.enum";
import {CounterpartyDto} from "../../counterparty/dto/counterparty.dto";

@Component({
  selector: 'app-register-consignment-note',
  templateUrl: './register-consignment-note.component.html',
  styleUrls: ['./register-consignment-note.component.css']
})
export class RegisterConsignmentNoteComponent implements OnInit {
  consignmentNoteForm: FormGroup;
  goodsDtoList: Array<GoodsDto> = [];
  consignmentNote: CreateConsignmentNoteDto;

  constructor(private fb: FormBuilder,
              private dialog: MatDialog) {
    this.consignmentNoteForm = fb.group({
      "number": [''],
      "shipment": [''],
      "counterparty": [''],
      "carrier": [''],
      "vehicleNumber": [''],
      "registration": [''],
      "consignmentNoteGoodsList": fb.array([]),
      "consignmentNoteType": [''],
      "consignmentNoteStatus": [''],
      "description": [''],
      //driver
    });
  }

  ngOnInit() {
  }

  onSubmit(consignmentNoteForm: FormGroup): void {
    console.log(this.consignmentNoteForm.value);
    consignmentNoteForm.reset();
  }

  addCounterparty(counterparty: CounterpartyDto): void {
    console.log(counterparty);
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
    console.log(carrier);
    // this.carrier = carrier;
    // this.consignmentNoteForm.controls['carrier'] = this.fb.group({
    //   "carrier": this.carrier
    // });
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
    console.log(goods);
    this.goodsDtoList.push(goods);
    let index = this.goodsDtoList.length - 1;
    (this.consignmentNoteForm.controls['consignmentNoteGoodsList'] as FormArray).push(this.fb.group({
      "goodsId": [this.goodsDtoList[index].id],
      "amount": ['']
    }));
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

}
