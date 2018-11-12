import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, Validators} from "@angular/forms";
import {WarehouseService} from "../service/warehouse.service";
import {AuthenticationService} from "../../auth/_services";

@Component({
  selector: 'app-create-warehouse',
  templateUrl: './create-warehouse.component.html',
  styleUrls: ['./create-warehouse.component.css']
})
export class CreateWarehouseComponent implements OnInit {

  warehouseNameControl: FormControl;
  error: any;

  constructor(private fb: FormBuilder,
              private warehouseService: WarehouseService,
              private auth: AuthenticationService) {
    this.warehouseNameControl = fb.control([''], Validators.required);
  }

  ngOnInit() {
  }

  onSubmit(warehouseNameControl: FormControl): void {
    this.warehouseService.saveWarehouse(this.auth.getCompanyId(), warehouseNameControl.value)
      .subscribe(long => {
          console.log(long);
        }, (err: any) => {
          this.error = err;
        }
      );
  }
}
