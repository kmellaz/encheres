import {Injectable, signal} from '@angular/core';
import {Client} from '../models/client';

@Injectable({
  providedIn: 'root',
})
export class ClientContextService {
  readonly clientSelectionne = signal<Client | null>(null);

  constructor() {
    console.log('constructor ClientContextService');
    const storedClient = sessionStorage.getItem('currentClient');
    if (storedClient) {
      this.clientSelectionne.set(JSON.parse(storedClient) as Client);
    }
  }

  setClient(client: Client): void {
    this.clientSelectionne.set(client);
    sessionStorage.setItem('currentClient', JSON.stringify(client));
    console.log('setClient() done !!');
  }

  clear(): void {
    this.clientSelectionne.set(null);
    sessionStorage.removeItem('currentClient');
    console.log('clear client done !!');
  }
}
