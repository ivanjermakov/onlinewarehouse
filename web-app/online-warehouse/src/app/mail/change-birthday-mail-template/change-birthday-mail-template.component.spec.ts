import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeBirthdayMailTemplateComponent } from './change-birthday-mail-template.component';

describe('ChangeBirthdayMailTemplateComponent', () => {
  let component: ChangeBirthdayMailTemplateComponent;
  let fixture: ComponentFixture<ChangeBirthdayMailTemplateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeBirthdayMailTemplateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeBirthdayMailTemplateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
