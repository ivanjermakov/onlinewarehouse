import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {GetCounterpartyComponent} from './get-counterparty.component';

describe('GetCounterpartyComponent', () => {
  let component: GetCounterpartyComponent;
  let fixture: ComponentFixture<GetCounterpartyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [GetCounterpartyComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetCounterpartyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
