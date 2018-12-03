import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateDriverDialogComponent} from './create-driver-dialog.component';

describe('CreateDriverDialogComponent', () => {
  let component: CreateDriverDialogComponent;
  let fixture: ComponentFixture<CreateDriverDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CreateDriverDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateDriverDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
