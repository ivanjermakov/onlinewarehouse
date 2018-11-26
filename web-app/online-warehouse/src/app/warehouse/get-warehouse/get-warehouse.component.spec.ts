import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {GetWarehouseComponent} from './get-warehouse.component';

describe('GetWarehouseComponent', () => {
  let component: GetWarehouseComponent;
  let fixture: ComponentFixture<GetWarehouseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [GetWarehouseComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetWarehouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
