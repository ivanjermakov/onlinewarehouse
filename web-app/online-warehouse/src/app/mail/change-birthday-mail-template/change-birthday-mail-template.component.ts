import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {MailService} from '../service/mail.service';
import {BirthdayMailTemplateDto} from '../dto/BirthdayMailTemplateDto';
import {BehaviorSubject, of} from 'rxjs';
import {catchError, debounceTime, distinctUntilChanged, finalize, tap} from 'rxjs/operators';

@Component({
  selector: 'app-change-birthday-mail-template',
  templateUrl: './change-birthday-mail-template.component.html',
  styleUrls: ['./change-birthday-mail-template.component.css']
})
export class ChangeBirthdayMailTemplateComponent implements OnInit {

  private templateForm: FormGroup;
  private template: BirthdayMailTemplateDto;

  constructor(private fb: FormBuilder, private mailService: MailService) {
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
    // TODO: don't forget to change to a variable
    this.mailService.getBirthdayMailTemplate(2).subscribe(data => {
      this.template = data;
      this.templateForm.controls['text'].setValue(this.template.text);
      this.templateForm.controls['backgroundColor'].setValue(this.template.backgroundColor);
      // will not set header image because it is probably makes no sense and throws error
      // this.templateForm.controls['headerImage'].setValue(this.template.headerImagePath);
    });
  }

}
