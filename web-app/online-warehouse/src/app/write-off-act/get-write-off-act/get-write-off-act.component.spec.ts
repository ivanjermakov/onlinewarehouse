import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetWriteOffActComponent } from './get-write-off-act.component';

describe('GetWriteOffActComponent', () => {
  let component: GetWriteOffActComponent;
  let fixture: ComponentFixture<GetWriteOffActComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetWriteOffActComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetWriteOffActComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
