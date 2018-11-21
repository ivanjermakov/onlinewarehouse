import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
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

  constructor(private fb: FormBuilder) {
    this.templateForm = fb.group({
      'text': [''],
      'backgroundColor': [''],
      'headerImage': ['']
    });
  }

  ngOnInit() {
  }

  loadBirthdayTemplate() {
  }

}
