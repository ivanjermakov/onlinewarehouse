import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsignmentNoteComponent } from './consignment-note.component';

describe('ConsignmentNoteComponent', () => {
  let component: ConsignmentNoteComponent;
  let fixture: ComponentFixture<ConsignmentNoteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsignmentNoteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsignmentNoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
