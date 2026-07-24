import {Component, OnInit, signal} from '@angular/core';
import {Client} from '../../models/client';
import {ClientService} from '../../services/client.service';
import {Router} from '@angular/router';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ClientContextService} from '../../services/client-context.service';

@Component({
  selector: 'app-clients',
  imports: [CommonModule, FormsModule],
  templateUrl: './clients.component.html',
  styleUrl: './clients.component.css',
})
export class ClientsComponent implements OnInit {
  _clients = signal<Client[]>([]);
  readonly clients = this._clients.asReadonly();
  selectedClient: Client | null = null ;

  constructor(private readonly clientService : ClientService,
              private readonly clientContext: ClientContextService,
              private readonly router: Router) {
    console.info('constructor Clients');
    this.clientContext.clear();
  }

  ngOnInit(): void {
    console.info('ngOnInit');
    this.loadClients();
  }

  loadClients(): void {
    this.clientService.getAll('').subscribe(cls => this._clients.set(cls));
  }

  onClientChange(client: Client): void {
    this.selectedClient = client;
    this.clientContext.setClient(client);
    this.router.navigate(['/encheres']);
  }
}
