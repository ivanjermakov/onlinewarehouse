import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DistributeGoodsWarehouseDialogComponent} from './distribute-goods-warehouse-dialog.component';

describe('DistributeGoodsWarehouseDialogComponent', () => {
  let component: DistributeGoodsWarehouseDialogComponent;
  let fixture: ComponentFixture<DistributeGoodsWarehouseDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [DistributeGoodsWarehouseDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DistributeGoodsWarehouseDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
