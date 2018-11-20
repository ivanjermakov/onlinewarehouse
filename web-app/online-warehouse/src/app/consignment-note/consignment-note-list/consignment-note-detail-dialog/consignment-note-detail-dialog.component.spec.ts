import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {ConsignmentNoteDetailDialogComponent} from './consignment-note-detail-dialog.component';

describe('ConsignmentNoteDetailDialogComponent', () => {
  let component: ConsignmentNoteDetailDialogComponent;
  let fixture: ComponentFixture<ConsignmentNoteDetailDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ConsignmentNoteDetailDialogComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsignmentNoteDetailDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
