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
  selectedClient: Client | null = null ;
  msgErreur = signal<string | null>(null);

  constructor(private auth: AuthService,
              private router: Router,
              private readonly clientContext: ClientContextService,
              private readonly clientService : ClientService) {}

  authenticate() {
    this.msgErreur.set(null);
    console.log('Login attempt with:', this.login, this.password);
    this.auth.login('/login', this.login, this.password)
      .subscribe({
        next: (response) => {
          console.log("Token reçu :", response.token);
          console.log("Token stocké dans localStorage:", localStorage.getItem('access_token'));
          this.setSelectedClient();
        },
        error: (err) => {
          console.error("Erreur login:", err);
          this.msgErreur.set(err.error.message);
        }
      });
  }

  setSelectedClient(): void {
    this.clientService.getAll('')
      .subscribe(cls => {
        this.selectedClient = cls.find(c => c.nom === this.login) || null;
        console.log("setSelectedClient : " + JSON.stringify(this.selectedClient));
        this.clientContext.setClient(this.selectedClient);
        this.router.navigate(['/encheres']);
      });
  }
}
