import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {WarehouseService} from "../service/warehouse.service";
import {ActivatedRoute, Router} from "@angular/router";
import {PlacementTypeEnum} from "../../shared/enum/placement-type.enum";
import {MeasurementUnitEnum} from "../../shared/enum/measurement-unit.enum";

@Component({
  selector: 'app-create-placement',
  templateUrl: './create-placement.component.html',
  styleUrls: ['./create-placement.component.css']
})
export class CreatePlacementComponent implements OnInit {

  createPlacementForm: FormGroup;
  error: any;

  placementType = PlacementTypeEnum;
  measurementUnit = MeasurementUnitEnum;

  constructor(private fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private warehouseService: WarehouseService) {
    this.createPlacementForm = fb.group({
      warehouseId: ['', Validators.required],
      size: ['', Validators.required],
      placementType: ['', Validators.required],
      measurementUnit: ['', Validators.required],
      storageCost: ['', Validators.required],
    });
  }

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    if (!Number.isNaN(id) && id != 0) {
      this.createPlacementForm.patchValue({'warehouseId': id});
    }
  }

  getPlacementType(): Array<string> {
    return Object.keys(PlacementTypeEnum);
  }

  getMeasurementUnit(): Array<string> {
    return Object.keys(MeasurementUnitEnum);
  }

  onSubmit(createWarehouseForm: FormGroup): void {
    this.warehouseService.savePlacement(this.createPlacementForm.value)
      .subscribe(long => {
          console.log(long);
        }, (err: any) => {
          this.error = err;
        }
      );
  }

}
