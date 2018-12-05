import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {WarehouseService} from "../service/warehouse.service";
import {CreateWarehouseDto} from "../dto/create-warehouse.dto";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

@Component({
  selector: 'app-create-warehouse',
  templateUrl: './create-warehouse.component.html',
  styleUrls: ['./create-warehouse.component.css']
})
export class CreateWarehouseComponent implements OnInit {

  createWarehouseForm: FormGroup;

  constructor(private fb: FormBuilder,
              private warehouseService: WarehouseService,
              private errorToast: RequestErrorToastHandlerService) {
    this.createWarehouseForm = fb.group({
      name: [[''], Validators.required],
      address: fb.group({
        country: ['', Validators.required],
        region: ['', Validators.required],
        locality: ['', Validators.required]
      })
    });
  }

  ngOnInit() {
  }

  onSubmit(createWarehouseForm: FormGroup): void {
    let warehouseDto = new CreateWarehouseDto();
    Object.assign(warehouseDto, createWarehouseForm.value);
    this.warehouseService.saveWarehouse(warehouseDto)
      .subscribe(long => {
          this.errorToast.handleSuccess('Warehouse saved successfully', 'Saved successfully');
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }
}
