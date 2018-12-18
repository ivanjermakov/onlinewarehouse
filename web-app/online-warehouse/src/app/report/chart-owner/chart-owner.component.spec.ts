import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ChartOwnerComponent} from './chart-owner.component';

describe('ChartOwnerComponent', () => {
  let component: ChartOwnerComponent;
  let fixture: ComponentFixture<ChartOwnerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ChartOwnerComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChartOwnerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
