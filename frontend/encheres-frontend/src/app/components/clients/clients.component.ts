import {Component} from '@angular/core';
import {Observable} from 'rxjs';
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
export class ClientsComponent {
  clients$!: Observable<Client[]>;
  selectedClient: Client | null = null ;

  constructor(private clientService : ClientService,
              private clientContext: ClientContextService,
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

  onClientChange(client: Client): void {
    this.selectedClient = client;
    this.clientContext.setClient(client);
    console.log('client changed : ' + JSON.stringify(client));
    this.router.navigate(['/encheres']);
  }
}
