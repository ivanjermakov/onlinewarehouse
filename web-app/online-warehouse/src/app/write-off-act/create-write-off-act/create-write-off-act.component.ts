import {Component, OnInit} from '@angular/core';
import {FormArray, FormBuilder, FormGroup} from "@angular/forms";
import {WriteOffActTypeEnum} from "../dto/enum/write-off-act-type.enum";
import {WriteOffActService} from "../service/write-off-act.service";
import {CreateWriteOffActDto} from "../dto/create-write-off-act.dto";
import {GoodsDto} from "../../shared/goods/goods.dto";
import {WriteOffTypeEnum} from "../dto/enum/write-off-type.enum";
import {MatDialog, MatDialogConfig} from "@angular/material";
import {GoodsListDialogComponent} from "../../shared/goods/goods-list-dialog/goods-list-dialog.component";

@Component({
  selector: 'app-create-write-off-act',
  templateUrl: './create-write-off-act.component.html',
  styleUrls: ['./create-write-off-act.component.css']
})
export class CreateWriteOffActComponent implements OnInit {

  goodsDtoList: Array<GoodsDto> = [];
  createWriteOffActForm: FormGroup;
  writeOffActType = WriteOffActTypeEnum;
  writeOffType = WriteOffTypeEnum;


  constructor(private fb: FormBuilder,
              private writeOffActService: WriteOffActService,
              private dialog: MatDialog) {
    this.createWriteOffActForm = fb.group({
      "responsiblePerson": [''],
      "writeOffActType": [''],
      "writeOffActGoodsDtoList": fb.array([])
    });
  }

  ngOnInit() {
  }

  getWriteOffActsTypes(): Array<string> {
    return Object.keys(this.writeOffActType);
  }

  getWriteOffTypes(): Array<string> {
    return Object.keys(this.writeOffType);
  }

  addGoods(goods: GoodsDto): void {
    this.goodsDtoList.push(goods);
    let index = this.goodsDtoList.length - 1;
    (this.createWriteOffActForm.controls['writeOffActGoodsDtoList'] as FormArray).push(this.fb.group({
      "goodsId": [this.goodsDtoList[index].id],
      "writeOffType": [''],
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

  onSubmit(createWriteOffActForm: FormGroup): void {
    let value = createWriteOffActForm.value;
    let createWriteOffActDto: CreateWriteOffActDto = new CreateWriteOffActDto(null, null, null, null);
    Object.assign(createWriteOffActDto, value);
    console.log(createWriteOffActDto);
    this.writeOffActService.saveWriteOffAct(2, createWriteOffActDto).subscribe((long: number) => {
      console.log(long)
    });
    this.clearFrom();
  }

  private clearFrom(): void {
    this.goodsDtoList = [];
    this.createWriteOffActForm.reset();
  }

}
