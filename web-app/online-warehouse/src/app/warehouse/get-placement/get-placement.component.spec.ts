import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetPlacementComponent } from './get-placement.component';

describe('GetPlacementComponent', () => {
  let component: GetPlacementComponent;
  let fixture: ComponentFixture<GetPlacementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetPlacementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetPlacementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
