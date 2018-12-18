import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsignmentNoteDetailComponent } from './consignment-note-detail.component';

describe('ConsignmentNoteDetailComponent', () => {
  let component: ConsignmentNoteDetailComponent;
  let fixture: ComponentFixture<ConsignmentNoteDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsignmentNoteDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsignmentNoteDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
