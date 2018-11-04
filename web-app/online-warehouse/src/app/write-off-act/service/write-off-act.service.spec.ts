import {TestBed} from '@angular/core/testing';

import {WriteOffActService} from './write-off-act.service';

describe('WriteOffActService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WriteOffActService = TestBed.get(WriteOffActService);
    expect(service).toBeTruthy();
  });
});
