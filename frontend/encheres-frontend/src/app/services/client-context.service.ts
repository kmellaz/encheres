import {Injectable, signal} from '@angular/core';
import {Client} from '../models/client';

@Injectable({
  providedIn: 'root',
})
export class ClientContextService {
  readonly clientSelectionne = signal<Client | null>(null);

  constructor() {
    const storedClient = sessionStorage.getItem('currentClient');
    if (storedClient) {
      this.clientSelectionne.set(JSON.parse(storedClient) as Client);
    }
  }

  setClient(client: Client | null): void {
    this.clientSelectionne.set(client);
    if (client) {
      sessionStorage.setItem('currentClient', JSON.stringify(client));
    } else {
      sessionStorage.removeItem('currentClient');
    }
  }

  clear(): void {
    this.clientSelectionne.set(null);
    sessionStorage.removeItem('currentClient');
  }
}
