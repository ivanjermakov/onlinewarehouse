import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCarrierDialogComponent } from './create-carrier-dialog.component';

describe('CreateCarrierDialogComponent', () => {
  let component: CreateCarrierDialogComponent;
  let fixture: ComponentFixture<CreateCarrierDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateCarrierDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateCarrierDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
