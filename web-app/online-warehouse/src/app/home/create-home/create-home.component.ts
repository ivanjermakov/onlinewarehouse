import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RequestErrorToastHandlerService} from "../../shared/toast/request-error-handler/request-error-toast-handler.service";
import {HomeService} from "../service/home.service";
import {HomeCard} from "../home-card";

@Component({
  selector: 'app-create-home',
  templateUrl: './create-home.component.html',
  styleUrls: ['./create-home.component.css']
})
export class CreateHomeComponent implements OnInit {

  private createHomeCardForm: FormGroup;

  constructor(private fb: FormBuilder,
              private homeService: HomeService,
              private errorToast: RequestErrorToastHandlerService) {
    this.createHomeCardForm = fb.group({
      "title": ['', [Validators.required, Validators.maxLength(50)]],
      "src": ['', [Validators.maxLength(80)]],
      "content": ['', [Validators.required]]
    })
  }

  ngOnInit() {
  }

  onSubmit() {
    let homeCard = new HomeCard();
    Object.assign(homeCard, this.createHomeCardForm.value, {company: null});
    this.homeService.saveCard(homeCard)
      .subscribe((id: number) => {
          this.errorToast.handleSuccess('New home card saved successfully', 'Saved successfully');
          this.createHomeCardForm.reset();
        }, (err: any) => {
          this.errorToast.handleError(err);
        }
      );
  }

  clearForm() {
    this.createHomeCardForm.reset()
  }

}
