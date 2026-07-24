import { TestBed } from '@angular/core/testing';

import { EncheresService } from './encheres.service';

describe('EncheresService', () => {
  let service: EncheresService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EncheresService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
