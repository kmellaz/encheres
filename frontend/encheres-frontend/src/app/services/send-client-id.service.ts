import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SendClientIdService {
  private clientSource = new BehaviorSubject<string|null>(null);
  client$ = this.clientSource.asObservable();

  setClient(client: string) {
    this.clientSource.next(client);
  }
}
