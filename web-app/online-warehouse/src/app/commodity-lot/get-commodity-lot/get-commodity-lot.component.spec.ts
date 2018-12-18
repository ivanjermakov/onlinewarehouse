import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetCommodityLotComponent } from './get-commodity-lot.component';

describe('GetCommodityLotComponent', () => {
  let component: GetCommodityLotComponent;
  let fixture: ComponentFixture<GetCommodityLotComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetCommodityLotComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetCommodityLotComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
