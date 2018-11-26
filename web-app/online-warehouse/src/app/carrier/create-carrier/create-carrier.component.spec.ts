import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCarrierComponent } from './create-carrier.component';

describe('CreateCarrierComponent', () => {
  let component: CreateCarrierComponent;
  let fixture: ComponentFixture<CreateCarrierComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateCarrierComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCarrierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
