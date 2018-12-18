import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CommodityLotListComponent } from './commodity-lot-list.component';

describe('CommodityLotListComponent', () => {
  let component: CommodityLotListComponent;
  let fixture: ComponentFixture<CommodityLotListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CommodityLotListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CommodityLotListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
