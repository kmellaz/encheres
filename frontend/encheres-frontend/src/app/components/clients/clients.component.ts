import { Component } from '@angular/core';
import {Observable} from 'rxjs';
import {Client} from '../../models/client';
import {ClientService} from '../../services/client.service';
import {Router} from '@angular/router';
import {SendClientIdService} from '../../services/send-client-id.service';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-clients',
  imports: [CommonModule, FormsModule],
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.css',
})
export class ClientsComponent {
  clients$!: Observable<Client[]>;
  selectedClientId: string | undefined ;

  constructor(private clientService : ClientService,
              private sendClientIdService: SendClientIdService,
              private router: Router) {
    console.info('constructor Clients');
  }

  ngOnInit(): void {
    console.info('ngOnInit');
    this.loadClients();
  }

  loadClients(): void {
    this.clients$ = this.clientService.getAll('');
  }

  goToEncheres(): void {
    if (this.selectedClientId) {
      this.sendClientIdService.setClient(this.selectedClientId);

      this.router.navigate(['/encheres']);
      console.info('Navigating to api/encheres for client ID: ' + this.selectedClientId);
    }
  }
}
