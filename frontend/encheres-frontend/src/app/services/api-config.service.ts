import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ApiConfigService {
  get baseUrl(): string {
    return environment.api.baseUrl;
  }

  get authEndpoints() {
    return environment.api.auth;
  }

  get encheresEndpoints() {
    return environment.api.encheres;
  }

  get clientsEndpoints() {
    return environment.api.clients;
  }

  buildUrl(endpoint: string, ...segments: string[]): string {
    const allSegments = [endpoint, ...segments].filter(s => s);
    return allSegments.join('/');
  }
}
