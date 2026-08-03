import {Component, OnInit, signal, ChangeDetectionStrategy} from '@angular/core';
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
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ClientsComponent implements OnInit {
  _clients = signal<Client[]>([]);
  readonly clients = this._clients.asReadonly();
  selectedClient: Client | null = null;
  isLoading = signal<boolean>(false);
  hasError = signal<string | null>(null);

  constructor(
    private clientService: ClientService,
    private clientContext: ClientContextService,
    private router: Router
  ) {
    this.clientContext.clear();
  }

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.isLoading.set(true);
    this.hasError.set(null);
    this.clientService.getAll().subscribe({
      next: (cls) => this._clients.set(cls),
      error: (err) => this.hasError.set('Erreur lors du chargement des clients'),
      complete: () => this.isLoading.set(false)
    });
  }

  onClientChange(client: Client): void {
    this.selectedClient = client;
    this.clientContext.setClient(client);
    this.router.navigate(['/encheres']);
  }
}
