import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {WarehouseService} from "../service/warehouse.service";
import {AuthenticationService} from "../../auth/_services";
import {CreateWarehouseDto} from "../dto/create-warehouse.dto";

@Component({
  selector: 'app-create-warehouse',
  templateUrl: './create-warehouse.component.html',
  styleUrls: ['./create-warehouse.component.css']
})
export class CreateWarehouseComponent implements OnInit {

  createWarehouseForm: FormGroup;
  error: any;

  constructor(private fb: FormBuilder,
              private warehouseService: WarehouseService) {
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
          console.log(long);
        }, (err: any) => {
          this.error = err;
        }
      );
  }
}
