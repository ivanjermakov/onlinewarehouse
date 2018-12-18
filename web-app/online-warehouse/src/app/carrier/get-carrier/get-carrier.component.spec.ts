import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {GetCarrierComponent} from './get-carrier.component';

describe('GetCarrierComponent', () => {
  let component: GetCarrierComponent;
  let fixture: ComponentFixture<GetCarrierComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [GetCarrierComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetCarrierComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
