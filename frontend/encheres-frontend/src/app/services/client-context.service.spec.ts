import { TestBed } from '@angular/core/testing';

import { ClientContextService } from './client-context.service';

describe('ClientContextService', () => {
  let service: ClientContextService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ClientContextService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
