import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateWriteOffActDialogComponent} from './create-write-off-act-dialog.component';

describe('CreateWriteOffActDialogComponent', () => {
  let component: CreateWriteOffActDialogComponent;
  let fixture: ComponentFixture<CreateWriteOffActDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CreateWriteOffActDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateWriteOffActDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
