import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CreateWriteOffActComponent} from './create-write-off-act.component';

describe('CreateWriteOffActComponent', () => {
  let component: CreateWriteOffActComponent;
  let fixture: ComponentFixture<CreateWriteOffActComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CreateWriteOffActComponent]
    })
      .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateWriteOffActComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
