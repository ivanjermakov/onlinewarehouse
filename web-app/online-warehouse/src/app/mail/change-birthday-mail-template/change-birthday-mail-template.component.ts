import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MailService} from '../service/mail.service';
import {BirthdayMailTemplateDto} from '../dto/BirthdayMailTemplateDto';
import {RequestErrorToastHandlerService} from '../../shared/toast/request-error-handler/request-error-toast-handler.service';

@Component({
  selector: 'app-change-birthday-mail-template',
  templateUrl: './change-birthday-mail-template.component.html',
  styleUrls: ['./change-birthday-mail-template.component.css']
})
export class ChangeBirthdayMailTemplateComponent implements OnInit {

  private templateForm: FormGroup;
  private template: BirthdayMailTemplateDto;

  constructor(private fb: FormBuilder, private mailService: MailService,
              private errorToast: RequestErrorToastHandlerService) {
    this.templateForm = fb.group({
      'text': [''],
      'backgroundColor': [''],
      'headerImagePath': ['']
    });
  }

  ngOnInit() {
    this.loadBirthdayTemplate();
  }

  loadBirthdayTemplate() {
    this.mailService.getBirthdayMailTemplate().subscribe(data => {
        this.template = data;
        this.templateForm.controls['text'].setValue(this.template.text);
        this.templateForm.controls['backgroundColor'].setValue(this.template.backgroundColor);
        // will not set header image because it is probably makes no sense and throws error
        // TODO: maybe should display header image somewhere on the page
        // this.templateForm.controls['headerImagePath'].setValue(this.template.headerImagePath);
      }, (err: any) => {
        this.errorToast.handleError(err);
      }
    );
  }

  editBirthdayMailTemplate() {
    this.template = this.templateForm.value;
    const image = (<HTMLInputElement>document.getElementById('fileToUpload')).files[0];
    this.mailService.editBirthdayMailTemplate(this.template, image, (dto) => {
      this.template = dto;
      this.templateForm.controls['text'].setValue(this.template.text);
      this.templateForm.controls['backgroundColor'].setValue(this.template.backgroundColor);
    });
  }
}
