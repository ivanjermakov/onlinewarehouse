import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {PlacementGoodsListDialogComponent} from './placement-goods-list-dialog.component';

describe('PlacementGoodsListDialogComponent', () => {
  let component: PlacementGoodsListDialogComponent;
  let fixture: ComponentFixture<PlacementGoodsListDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [PlacementGoodsListDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PlacementGoodsListDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
