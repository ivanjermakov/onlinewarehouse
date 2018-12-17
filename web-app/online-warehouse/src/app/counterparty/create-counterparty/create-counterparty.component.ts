import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CounterpartyService} from "../service/counterparty.service";
import {CreateCounterpartyDto} from "../dto/create-counterparty.dto";
import {AuthenticationService} from "../../auth/_services";
import {CounterpartyTypeEnum} from "../dto/enum/counterparty-type.enum";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";
import {Observable} from "rxjs";
import {AddressService} from "../../shared/address/service/address.service";
import {map, startWith} from "rxjs/operators";

@Component({
  selector: 'app-create-counterparty',
  templateUrl: './create-counterparty.component.html',
  styleUrls: ['./create-counterparty.component.css']
})
export class CreateCounterpartyComponent implements OnInit {

  @Input() counterpartyTypeInput: CounterpartyTypeEnum;

  @Output() submitted: EventEmitter<any[]> = new EventEmitter();

  createCounterpartyForm: FormGroup;
  createCounterparty: CreateCounterpartyDto = new CreateCounterpartyDto();

  public countryOptions: any[];
  countryFilteredOptions: Observable<any[]>;
  public regionOptions: any[];
  regionFilteredOptions: Observable<any[]>;

  constructor(private fb: FormBuilder,
              private counterpartyService: CounterpartyService,
              private auth: AuthenticationService,
              private addressService: AddressService,
              private errorToast: RequestErrorToastHandlerService) {
    this.createCounterpartyForm = fb.group({
      name: ['', [Validators.required, Validators.maxLength(50)]],
      counterpartyType: ['', Validators.required],
      taxNumber: ['', [Validators.required, Validators.maxLength(15)]],
      address: fb.group({
        country: ['', [Validators.required, Validators.maxLength(40)]],
        region: ['', [Validators.required, Validators.maxLength(55)]],
        locality: ['', [Validators.required, Validators.maxLength(50)]],
      })
    });
  }

  ngOnInit() {
    if (this.counterpartyTypeInput) {
      this.createCounterpartyForm.removeControl('counterpartyType');
      this.createCounterparty.counterpartyType = this.counterpartyTypeInput;
    }
    this.addressService.getCountries().subscribe((result) => {
      this.countryOptions = result;
      this.countryFilteredOptions = (this.createCounterpartyForm.controls['address'] as FormGroup).controls['country'].valueChanges
        .pipe(
          startWith<string | Object>(''),
          map((value: any) => typeof value === 'string' || value === null ? value : value.title),
          map(title => title ? this._countryFilter(title) : this.countryOptions.slice())
        )
      ;
    });
  }

  getCounterpartyTypes(): Array<string> {
    return Object.keys(CounterpartyTypeEnum);
  }

  onSubmit(createCounterpartyForm: FormGroup): void {
    let value = createCounterpartyForm.value;
    this.createCounterparty.companyId = this.auth.getCompanyId();
    Object.assign(this.createCounterparty, value);
    this.counterpartyService.saveCounterparty(this.createCounterparty)
      .subscribe((long: number) => {
          this.errorToast.handleSuccess('Counterparty saved successfully', 'Saved successfully');
          this.submitted.emit();
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
    (this.createCounterpartyForm.controls['address'] as FormGroup).controls['country'].patchValue(event.option.value.title);
    if (id !== 0) {
      this.addressService.getRegions(id).subscribe((result) => {
        this.regionOptions = result;
        this.regionFilteredOptions = (this.createCounterpartyForm.controls['address'] as FormGroup).controls['region'].valueChanges
          .pipe(
            startWith<string | Object>(''),
            map((value: any) => typeof value === 'string' || value === null ? value : value.title),
            map(title => title ? this._regionFilter(title) : this.regionOptions.slice())
          );
        (this.createCounterpartyForm.controls['address'] as FormGroup).controls['country'].valueChanges
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
