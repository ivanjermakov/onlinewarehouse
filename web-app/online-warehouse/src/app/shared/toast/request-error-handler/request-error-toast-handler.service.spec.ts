import {TestBed} from '@angular/core/testing';

import {RequestErrorToastHandlerService} from './request-error-toast-handler.service';

describe('RequestErrorToastHandlerService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RequestErrorToastHandlerService = TestBed.get(RequestErrorToastHandlerService);
    expect(service).toBeTruthy();
  });
});
