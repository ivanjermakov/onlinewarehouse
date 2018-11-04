import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {WriteOffActListComponent} from './write-off-act-list.component';

describe('WriteOffActListComponent', () => {
  let component: WriteOffActListComponent;
  let fixture: ComponentFixture<WriteOffActListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [WriteOffActListComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WriteOffActListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
