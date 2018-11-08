import { Component, OnInit } from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {CreateConsignmentNoteDto} from "../dto/create-consignment-note-dto";
import {GoodsListDialogComponent} from "../../shared/goods/goods-list-dialog/goods-list-dialog.component";
import {GoodsDto} from "../../shared/goods/goods.dto";
import {MatDialog, MatDialogConfig} from "@angular/material";

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
      "shipment": ['']
    });
  }

  ngOnInit() {
  }

  onSubmit(consignmentNoteForm: FormGroup): void {
    console.log(this.consignmentNoteForm.value);
    consignmentNoteForm.reset();
  }

  addGoods(goods: GoodsDto): void {
    this.goodsDtoList.push(goods);
    let index = this.goodsDtoList.length - 1;
    (this.consignmentNoteForm.controls['consignmentNoteGoodsList'] as FormArray).push(this.fb.group({
      "goodsId": [this.goodsDtoList[index].id],
      "amount": ['']
    }));
  }


  openModal(): void {
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
