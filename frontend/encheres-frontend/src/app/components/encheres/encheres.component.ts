import {Component, inject, OnInit, signal, ChangeDetectionStrategy} from '@angular/core';
import {Router, RouterModule} from '@angular/router';
import {Enchere} from '../../models/enchere';
import {EncheresService} from '../../services/encheres.service';
import {CommonModule, Location} from '@angular/common';
import {ClientContextService} from '../../services/client-context.service';

@Component({
  selector: 'app-encheres',
  imports: [CommonModule, RouterModule],
  templateUrl: './encheres.component.html',
  styleUrl: './encheres.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EncheresComponent implements OnInit {
  clientContext = inject(ClientContextService);

  private readonly _encheres = signal<Enchere[]>([]);
  encheres = this._encheres.asReadonly();

  isLoading = signal<boolean>(false);
  hasError = signal<string | null>(null);

  constructor(
    private enchereService: EncheresService,
    private location: Location,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadEncheres();
  }

  loadEncheres(): void {
    this.isLoading.set(true);
    this.hasError.set(null);

    this.enchereService.getAll()
      .subscribe({
        next: (encheres) => this._encheres.set(encheres),
        error: (err) => this.hasError.set('Erreur lors du chargement des enchères'),
        complete: () => this.isLoading.set(false)
      });
  }

  goToDetail(id: number): void {
    if (id) {
      this.router.navigate(['/detailEnchere', id]);
    }
  }

  getTempsRestant(dateFin: Date | string): string {
    const fin = dateFin instanceof Date ? dateFin : new Date(dateFin);
    const maintenant = new Date();
    const diff = fin.getTime() - maintenant.getTime();

    if (diff <= 0) {
      return 'Enchère terminée';
    }

    const jours = Math.floor(diff / (1000 * 60 * 60 * 24));
    const heures = Math.floor((diff / (1000 * 60 * 60)) % 24);
    const minutes = Math.floor((diff / (1000 * 60)) % 60);
    const secondes = Math.floor((diff / 1000) % 60);

    return `${jours}j ${heures}h ${minutes}m ${secondes}s`;
  }

  goBack(): void {
    this.location.back();
  }
}
