import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {WarehouseService} from "../service/warehouse.service";
import {CreateWarehouseDto} from "../dto/create-warehouse.dto";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";
import {Observable} from "rxjs";
import {AddressService} from "../../shared/address/service/address.service";
import {map, startWith} from "rxjs/operators";

@Component({
  selector: 'app-create-warehouse',
  templateUrl: './create-warehouse.component.html',
  styleUrls: ['./create-warehouse.component.css']
})
export class CreateWarehouseComponent implements OnInit {

  createWarehouseForm: FormGroup;

  public countryOptions: any[];
  countryFilteredOptions: Observable<any[]>;
  public regionOptions: any[];
  regionFilteredOptions: Observable<any[]>;

  constructor(private fb: FormBuilder,
              private warehouseService: WarehouseService,
              private addressService: AddressService,
              private errorToast: RequestErrorToastHandlerService) {
    this.createWarehouseForm = fb.group({
      name: [[''], [Validators.required, Validators.maxLength(20)]],
      address: fb.group({
        country: ['', [Validators.required, Validators.maxLength(40)]],
        region: ['', [Validators.required, Validators.maxLength(55)]],
        locality: ['', [Validators.required, Validators.maxLength(50)]]
      })
    });
  }

  ngOnInit() {
    this.addressService.getCountries().subscribe((result) => {
      this.countryOptions = result;
      this.countryFilteredOptions = (this.createWarehouseForm.controls['address'] as FormGroup).controls['country'].valueChanges
        .pipe(
          startWith<string | Object>(''),
          map((value: any) => typeof value === 'string' || value === null ? value : value.title),
          map(title => title ? this._countryFilter(title) : this.countryOptions.slice())
        )
      ;
    });
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

  regionDisplayFn(region?: any): string | undefined {
    return region ? region : undefined;
  }

  getRegions(event) {
    let id = event.option.value.id;
    (this.createWarehouseForm.controls['address'] as FormGroup).controls['country'].patchValue(event.option.value.title);
    if (id !== 0) {
      this.addressService.getRegions(id).subscribe((result) => {
        this.regionOptions = result;
        this.regionFilteredOptions = (this.createWarehouseForm.controls['address'] as FormGroup).controls['region'].valueChanges
          .pipe(
            startWith<string | Object>(''),
            map((value: any) => typeof value === 'string' || value === null ? value : value.title),
            map(title => title ? this._regionFilter(title) : this.regionOptions.slice())
          );
        (this.createWarehouseForm.controls['address'] as FormGroup).controls['country'].valueChanges
          .subscribe(() => this.regionOptions = [])
      });
    }
  }


  private _countryFilter(title: string): Object[] {
    const filterValue = title.toLowerCase();

    return this.countryOptions.filter(option => option.title.toLowerCase().indexOf(filterValue) === 0);
  }

  private _regionFilter(title: string): Object[] {
    const filterValue = title.toLowerCase();

    return this.regionOptions.filter(option => option.title.toLowerCase().indexOf(filterValue) === 0);
  }

}
