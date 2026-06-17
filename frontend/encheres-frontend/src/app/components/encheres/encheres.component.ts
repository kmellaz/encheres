import {Component} from '@angular/core';
import {Observable} from 'rxjs';
import {RouterModule} from '@angular/router';
import {Enchere} from '../../models/enchere';
import {EncheresService} from '../../services/encheres.service';
import {CommonModule, Location} from '@angular/common';
import {ClientContextService} from '../../services/client-context.service';

@Component({
  selector: 'app-encheres',
  imports: [CommonModule, RouterModule],
  templateUrl: './encheres.component.html',
  styleUrl: './encheres.component.css',
})
export class EncheresComponent {
  encheres$!: Observable<Enchere[]>;

  constructor(private enchereService : EncheresService,
              private clientContext: ClientContextService,
              private location: Location) {
    console.info('constructor EncheresComponent');
  }

  ngOnInit(): void {
    console.info('ngOnInit dans EncheresComponent');
    console.info('client connecté : ' + this.clientContext.clientSelectionne()?.nom + ' ' + this.clientContext.clientSelectionne()?.prenom);
    this.loadEncheres();
  }

  loadEncheres(): void {
    this.encheres$ = this.enchereService.getAll('');
  }


  getTempsRestant(dateFin: Date | string): string {
    const fin =
      dateFin instanceof Date
        ? dateFin
        : new Date(dateFin);

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
