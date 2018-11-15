import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateCounterpartyDialogComponent} from './create-counterparty-dialog.component';

describe('CreateCounterpartyDialogComponent', () => {
  let component: CreateCounterpartyDialogComponent;
  let fixture: ComponentFixture<CreateCounterpartyDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CreateCounterpartyDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCounterpartyDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
