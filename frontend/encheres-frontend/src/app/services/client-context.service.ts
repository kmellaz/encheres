import {Injectable, signal} from '@angular/core';
import {Client} from '../models/client';

@Injectable({
  providedIn: 'root',
})
export class ClientContextService {
  readonly clientSelectionne = signal<Client | null>(null);

  setClient(client: Client): void {
    this.clientSelectionne.set(client);
  }

  clear(): void {
    this.clientSelectionne.set(null);
  }
}
