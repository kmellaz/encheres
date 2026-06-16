import { TestBed } from '@angular/core/testing';

import { SendClientIdService } from './send-client-id.service';

describe('SendClientIdService', () => {
  let service: SendClientIdService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SendClientIdService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
