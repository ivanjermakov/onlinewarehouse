import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CreateCarrierDto} from "../dto/create-carrier.dto";
import {CarrierService} from "../service/carrier.service";
import {CarrierTypeEnum} from "../dto/enum/carrier-type.enum";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";

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

  constructor(private fb: FormBuilder,
              private carrierService: CarrierService,
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

}
