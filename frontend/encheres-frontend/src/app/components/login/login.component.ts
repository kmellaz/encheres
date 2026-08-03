import {Component, signal} from '@angular/core';
import {AuthService} from '../../services/auth.service';
import {Router, RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';
import {ClientContextService} from '../../services/client-context.service';
import {Client} from '../../models/client';
import {ClientService} from '../../services/client.service';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  login = '';
  password = '';
  selectedClient: Client | null = null;
  msgErreur = signal<string | null>(null);
  isLoading = signal<boolean>(false);

  constructor(
    private auth: AuthService,
    private router: Router,
    private clientContext: ClientContextService,
    private clientService: ClientService
  ) {}

  authenticate(): void {
    if (!this.validateForm()) {
      return;
    }

    this.msgErreur.set(null);
    this.isLoading.set(true);

    this.auth.login(this.login, this.password)
      .subscribe({
        next: () => this.handleLoginSuccess(),
        error: (err) => this.handleLoginError(err),
        complete: () => this.isLoading.set(false)
      });
  }

  private validateForm(): boolean {
    if (!this.login.trim() || !this.password.trim()) {
      this.msgErreur.set('Veuillez remplir tous les champs');
      return false;
    }
    return true;
  }

  private handleLoginSuccess(): void {
    this.setSelectedClient();
  }

  private handleLoginError(err: any): void {
    const message = err?.error?.message || 'Erreur de connexion';
    this.msgErreur.set(message);
  }

  private setSelectedClient(): void {
    this.clientService.getAll()
      .subscribe({
        next: (clients) => {
          this.selectedClient = clients.find(c => c.nom === this.login) || null;
          this.clientContext.setClient(this.selectedClient);
          this.router.navigate(['/encheres']);
        },
        error: (err) => this.msgErreur.set('Erreur lors de la récupération du client')
      });
  }
}
