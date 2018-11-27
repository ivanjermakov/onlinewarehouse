import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {RegisterConsignmentNoteDialogComponent} from './register-consignment-note-dialog.component';

describe('RegisterConsignmentNoteDialogComponent', () => {
  let component: RegisterConsignmentNoteDialogComponent;
  let fixture: ComponentFixture<RegisterConsignmentNoteDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [RegisterConsignmentNoteDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterConsignmentNoteDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
