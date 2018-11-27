import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DistributeGoodsWarehouseComponent } from './distribute-goods-warehouse.component';

describe('DistributeGoodsWarehouseComponent', () => {
  let component: DistributeGoodsWarehouseComponent;
  let fixture: ComponentFixture<DistributeGoodsWarehouseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DistributeGoodsWarehouseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DistributeGoodsWarehouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
