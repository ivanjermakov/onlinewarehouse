import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CounterpartyService} from "../service/counterparty.service";
import {CreateCounterpartyDto} from "../dto/create-counterparty.dto";
import {AuthenticationService} from "../../auth/_services";
import {CounterpartyTypeEnum} from "../dto/enum/counterparty-type.enum";

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

  constructor(private fb: FormBuilder,
              private counterpartyService: CounterpartyService,
              private auth: AuthenticationService) {
    this.createCounterpartyForm = fb.group({
      name: ['', Validators.required],
      counterpartyType: ['', Validators.required],
      taxNumber: ['', Validators.required],
      address: fb.group({
        country: ['', Validators.required],
        region: ['', Validators.required],
        locality: ['', Validators.required]
      })
    });
  }

  ngOnInit() {
    if (this.counterpartyTypeInput) {
      this.createCounterpartyForm.removeControl('counterpartyType');
      this.createCounterparty.counterpartyType = this.counterpartyTypeInput;
    }
  }

  getCounterpartyTypes(): Array<string> {
    return Object.keys(CounterpartyTypeEnum);
  }

  onSubmit(createCounterpartyForm: FormGroup): void {
    let value = createCounterpartyForm.value;
    this.createCounterparty.companyId = this.auth.getCompanyId();
    Object.assign(this.createCounterparty, value);
    this.counterpartyService.saveCounterparty(this.createCounterparty).subscribe((long: number) => {
      console.log(long);
      this.submitted.emit();
    });
  }

}
