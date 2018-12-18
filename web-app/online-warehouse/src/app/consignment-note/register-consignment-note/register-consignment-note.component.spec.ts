import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterConsignmentNoteComponent } from './register-consignment-note.component';

describe('RegisterConsignmentNoteComponent', () => {
  let component: RegisterConsignmentNoteComponent;
  let fixture: ComponentFixture<RegisterConsignmentNoteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterConsignmentNoteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterConsignmentNoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
