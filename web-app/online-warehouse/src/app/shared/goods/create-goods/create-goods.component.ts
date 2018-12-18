import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RequestErrorToastHandlerService} from "../../toast/request-error-handler/request-error-toast-handler.service";
import {PlacementTypeEnum} from "../../enum/placement-type.enum";
import {MeasurementUnitEnum} from "../../enum/measurement-unit.enum";
import {GoodService} from "../good.service";

@Component({
  selector: 'app-create-goods',
  templateUrl: './create-goods.component.html',
  styleUrls: ['./create-goods.component.css']
})
export class CreateGoodsComponent implements OnInit {

  @Output() submitted: EventEmitter<any[]> = new EventEmitter();

  private createGoodsForm: FormGroup;
  private placementTypeEnum = PlacementTypeEnum;

  constructor(private fb: FormBuilder,
              private goodsService: GoodService,
              private errorToast: RequestErrorToastHandlerService) {
    this.createGoodsForm = fb.group({
      "name": ['', [Validators.required, Validators.maxLength(70)]],
      "placementType": ['', [Validators.required]],
      "measurementUnit": ['', [Validators.required]],
      "cost": ['', [Validators.required, Validators.min(0)]],
      "weight": ['', [Validators.required, Validators.min(0)]],
      "labelling": ['', [Validators.required, Validators.maxLength(50)] ],
      "description": ['']
    })
  }

  ngOnInit() {
  }

  getPlacementTypes(): string[] {
    return Object.keys(PlacementTypeEnum)
  }

  getMeasurementUnits(): string[] {
    return Object.keys(MeasurementUnitEnum)
  }

  onSubmit() {
    this.goodsService.saveGoods(this.createGoodsForm.value)
      .subscribe((id: number) => {
          this.errorToast.handleSuccess('New goods saved successfully', 'Saved successfully');
          this.createGoodsForm.reset();
          this.submitted.emit();
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }

}
