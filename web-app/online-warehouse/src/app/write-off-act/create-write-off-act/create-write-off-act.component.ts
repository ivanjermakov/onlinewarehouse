import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormArray, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {WriteOffActTypeEnum} from "../dto/enum/write-off-act-type.enum";
import {WriteOffActService} from "../service/write-off-act.service";
import {CreateWriteOffActDto} from "../dto/create-write-off-act.dto";
import {GoodsDto} from "../../shared/goods/dto/goods.dto";
import {WriteOffTypeEnum} from "../dto/enum/write-off-type.enum";
import {MatDialog, MatDialogConfig} from "@angular/material";
import {GoodsListDialogComponent} from "../../shared/goods/goods-list-dialog/goods-list-dialog.component";
import {AuthenticationService} from "../../auth/_services";

@Component({
  selector: 'app-create-write-off-act',
  templateUrl: './create-write-off-act.component.html',
  styleUrls: ['./create-write-off-act.component.css']
})
export class CreateWriteOffActComponent implements OnInit {

  @Input() emitWhenSubmit: boolean = false;
  @Input() inputGoods: GoodsDto[];

  @Output() submitted: EventEmitter<CreateWriteOffActDto> = new EventEmitter<CreateWriteOffActDto>();

  goodsDtoList: Array<GoodsDto> = [];
  createWriteOffActForm: FormGroup;
  writeOffActType = WriteOffActTypeEnum;
  writeOffType = WriteOffTypeEnum;

  goodsStep: FormControl;

  constructor(private fb: FormBuilder,
              private writeOffActService: WriteOffActService,
              private dialog: MatDialog,
              private auth: AuthenticationService) {
    this.createWriteOffActForm = fb.group({
      "responsiblePerson": ['', Validators.required],
      "writeOffActType": ['', Validators.required],
      "writeOffActGoodsDtoList": fb.array([], Validators.required)
    });
  }

  ngOnInit() {
  }

  printToConsole(smth: any) {
    console.log(smth)
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
      "writeOffType": ['', Validators.required],
      "amount": ['', Validators.required]
    }));
  }

  deleteItem(i: number): void {
    this.goodsDtoList.splice(i, 1);
    (this.createWriteOffActForm.controls['writeOffActGoodsDtoList'] as FormArray).removeAt(i);
  }

  openModal(): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    if (this.inputGoods) {
      dialogConfig.data = {inputGoods: this.inputGoods};
    }
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
    let createWriteOffActDto: CreateWriteOffActDto = new CreateWriteOffActDto(this.auth.getUserId(), null, null, null);
    Object.assign(createWriteOffActDto, value);
    console.log(createWriteOffActDto);
    if (!this.emitWhenSubmit) {
      this.writeOffActService.saveWriteOffAct(createWriteOffActDto).subscribe((long: number) => {
        console.log(long)
      });
    } else {
      this.submitted.emit(createWriteOffActDto);
    }
    this.clearFrom();
  }

  private clearFrom(): void {
    this.goodsDtoList = [];
    this.createWriteOffActForm.reset();
  }

}
