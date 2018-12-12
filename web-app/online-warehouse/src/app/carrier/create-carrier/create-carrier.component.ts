import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreateCarrierDto} from "../dto/create-carrier.dto";
import {CarrierService} from "../service/carrier.service";
import {CarrierTypeEnum} from "../dto/enum/carrier-type.enum";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";
import {map, startWith} from "rxjs/operators";
import {Observable} from "rxjs";
import {AddressService} from "../../shared/address/service/address.service";

@Component({
  selector: 'app-create-carrier',
  templateUrl: './create-carrier.component.html',
  styleUrls: ['./create-carrier.component.css']
})
export class CreateCarrierComponent implements OnInit {

  @Output() submitted: EventEmitter<any[]> = new EventEmitter();
  createCarrierForm: FormGroup;
  createCarrier: CreateCarrierDto = new CreateCarrierDto();
  carrierTypeEnum = CarrierTypeEnum;
  private error: any;

  public countryOptions: any[];
  countryFilteredOptions: Observable<any[]>;
  public regionOptions: any[];
  regionFilteredOptions: Observable<any[]>;

  constructor(private fb: FormBuilder,
              private carrierService: CarrierService,
              private addressService: AddressService,
              private errorToast: RequestErrorToastHandlerService) {
    this.createCarrierForm = fb.group({
      name: ['', Validators.required],
      carrierType: ['', Validators.required],
      trusted: [false, Validators.required],
      taxNumber: ['', Validators.required],
      address: fb.group({
        country: ['', Validators.required],
        region: ['', Validators.required],
        locality: ['', Validators.required]
      })
    });
  }


  ngOnInit() {
    this.addressService.getCountries().subscribe((result) => {
      this.countryOptions = result;
      this.countryFilteredOptions = (this.createCarrierForm.controls['address'] as FormGroup).controls['country'].valueChanges
        .pipe(
          startWith<string | Object>(''),
          map((value: any) => typeof value === 'string' || value === null ? value : value.title),
          map(title => title ? this._countryFilter(title) : this.countryOptions.slice())
        )
      ;
    });
  }

  getCarrierTypes(): Array<string> {
    return Object.keys(CarrierTypeEnum);
  }

  onSubmit(createCarrierForm: FormGroup): void {
    let value = createCarrierForm.value;
    Object.assign(this.createCarrier, value);
    this.carrierService.saveCarrier(this.createCarrier)
      .subscribe((long: number) => {
          this.errorToast.handleSuccess('New carrier saved  successfully', 'Saved  successfully');
          this.submitted.emit();
        }, (err: any) => {
          this.error = err;
          this.errorToast.handleError(err)
        }
      );
  }

  regionDisplayFn(region?: any): string | undefined {
    return region ? region : undefined;
  }

  getRegions(event) {
    let id = event.option.value.id;
    (this.createCarrierForm.controls['address'] as FormGroup).controls['country'].patchValue(event.option.value.title);
    if (id !== 0) {
      this.addressService.getRegions(id).subscribe((result) => {
        this.regionOptions = result;
        this.regionFilteredOptions = (this.createCarrierForm.controls['address'] as FormGroup).controls['region'].valueChanges
          .pipe(
            startWith<string | Object>(''),
            map((value: any) => typeof value === 'string' || value === null ? value : value.title),
            map(title => title ? this._regionFilter(title) : this.regionOptions.slice())
          );
        (this.createCarrierForm.controls['address'] as FormGroup).controls['country'].valueChanges
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
