import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateCounterpartyComponent} from './create-counterparty.component';

describe('CreateCounterpartyComponent', () => {
  let component: CreateCounterpartyComponent;
  let fixture: ComponentFixture<CreateCounterpartyComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CreateCounterpartyComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCounterpartyComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
