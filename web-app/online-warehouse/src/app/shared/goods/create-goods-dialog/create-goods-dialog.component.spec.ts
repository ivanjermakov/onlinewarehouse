import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateGoodsDialogComponent} from './create-goods-dialog.component';

describe('CreateGoodsDialogComponent', () => {
  let component: CreateGoodsDialogComponent;
  let fixture: ComponentFixture<CreateGoodsDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CreateGoodsDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateGoodsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
