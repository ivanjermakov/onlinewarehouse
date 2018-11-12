import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsignmentNoteListComponent } from './consignment-note-list.component';

describe('ConsignmentNoteListComponent', () => {
  let component: ConsignmentNoteListComponent;
  let fixture: ComponentFixture<ConsignmentNoteListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsignmentNoteListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsignmentNoteListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
