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
import {PlacementGoodsDto} from "../../warehouse/dto/placement-goods.dto";
import {PlacementGoodsListDialogComponent} from "../../warehouse/placement-goods-list-dialog/placement-goods-list-dialog.component";
import {PlacementCreateWriteOffActDto} from "../dto/placement-create-write-off-act.dto";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Component({
  selector: 'app-create-write-off-act',
  templateUrl: './create-write-off-act.component.html',
  styleUrls: ['./create-write-off-act.component.css']
})
export class CreateWriteOffActComponent implements OnInit {

  @Input() emitWhenSubmit: boolean = false;
  @Input() inputGoods: GoodsDto[];
  @Input() placementGoods: PlacementGoodsDto[];

  @Output() submitted: EventEmitter<CreateWriteOffActDto> = new EventEmitter<CreateWriteOffActDto>();
  @Output() placementSubmitted: EventEmitter<PlacementCreateWriteOffActDto> = new EventEmitter<PlacementCreateWriteOffActDto>();

  goodsDtoList: Array<GoodsDto> = [];
  placementGoodsDtoList: Array<PlacementGoodsDto> = [];
  createWriteOffActForm: FormGroup;
  writeOffActType = WriteOffActTypeEnum;
  writeOffType = WriteOffTypeEnum;

  goodsStep: FormControl;

  constructor(private fb: FormBuilder,
              private writeOffActService: WriteOffActService,
              private dialog: MatDialog,
              private auth: AuthenticationService,
              private errorToast: RequestErrorToastHandlerService) {
    this.createWriteOffActForm = fb.group({
      "responsiblePerson": ['', Validators.required],
      "writeOffActType": ['', Validators.required],
      "writeOffActGoodsDtoList": fb.array([], Validators.required)
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
      "writeOffType": ['', Validators.required],
      "amount": ['', Validators.required]
    }));
  }

  addPlacementGoods(goods: PlacementGoodsDto): void {
    this.placementGoodsDtoList.push(goods);
    console.log(this.placementGoodsDtoList);
    let index = this.placementGoodsDtoList.length - 1;
    (this.createWriteOffActForm.controls['writeOffActGoodsDtoList'] as FormArray).push(this.fb.group({
      "placementGoods": [this.placementGoodsDtoList[index]],
      "writeOffType": ['', Validators.required],
      "amount": ['', [Validators.required, Validators.min(1), Validators.max(this.placementGoodsDtoList[index].amount)]]
    }));
  }

  deleteItem(i: number): void {
    this.goodsDtoList.splice(i, 1);
    (this.createWriteOffActForm.controls['writeOffActGoodsDtoList'] as FormArray).removeAt(i);
  }

  deletePlacementItem(i: number): void {
    this.placementGoodsDtoList.splice(i, 1);
    (this.createWriteOffActForm.controls['writeOffActGoodsDtoList'] as FormArray).removeAt(i);
  }

  openModal(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = false;
    dialogConfig.autoFocus = true;
    if (this.inputGoods) {
      dialogConfig.data = {inputGoods: this.inputGoods};
    }
    let dialogRef;
    if (this.placementGoods) {
      dialogConfig.data = {placementGoods: this.placementGoods};
      dialogRef = this.dialog.open(PlacementGoodsListDialogComponent, dialogConfig);

      dialogRef.afterClosed().subscribe(
        data => {
          console.log('dialog closed with', data);

          if (data) {
            this.addPlacementGoods(data);
          }
        }
      );
    } else {
      dialogRef = this.dialog.open(GoodsListDialogComponent, dialogConfig);

      dialogRef.afterClosed().subscribe(
        data => {
          if (data) {
            this.addGoods(data);
          }
        }
      );
    }
  }

  onSubmit(createWriteOffActForm: FormGroup): void {
    let value = createWriteOffActForm.value;
    console.log('Form value', createWriteOffActForm.value);
    let createWriteOffActDto: CreateWriteOffActDto = new CreateWriteOffActDto(this.auth.getUserId(), null, null, null);
    Object.assign(createWriteOffActDto, value);
    console.log(createWriteOffActDto);
    if (!this.emitWhenSubmit) {
      this.writeOffActService.saveWriteOffAct(createWriteOffActDto)
        .subscribe((long: number) => {
            this.errorToast.handleSuccess('Write-off case saved successfully', 'Saved successfully');
          }, (err: any) => {
            this.errorToast.handleError(err);
          }
        );
    } else {
      if (this.placementGoods) {
        this.placementSubmitted.emit(new PlacementCreateWriteOffActDto(
          this.auth.getUserId(),
          value.responsiblePerson,
          value.writeOffActType,
          value.writeOffActGoodsDtoList
        ))
      } else {
        this.submitted.emit(createWriteOffActDto);
      }
    }
    this.clearFrom();
  }

  private clearFrom(): void {
    this.goodsDtoList = [];
    this.createWriteOffActForm.reset();
  }

}
