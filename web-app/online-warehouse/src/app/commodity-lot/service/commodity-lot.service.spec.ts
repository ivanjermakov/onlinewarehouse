import { TestBed } from '@angular/core/testing';

import { CommodityLotService } from './commodity-lot.service';

describe('CommodityLotService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CommodityLotService = TestBed.get(CommodityLotService);
    expect(service).toBeTruthy();
  });
});
