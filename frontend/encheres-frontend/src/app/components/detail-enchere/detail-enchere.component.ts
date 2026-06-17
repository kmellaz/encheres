import {Component, signal} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {EncheresService} from '../../services/encheres.service';
import {Enchere} from '../../models/enchere';
import {CommonModule, Location} from '@angular/common';
import {TypeEnchere} from '../../models/enums/type-enchere.enum';
import {OffreMontantComponent} from '../offre-montant/offre-montant.component';
import {OffreRequest} from '../../models/offre-request';
import {ClientContextService} from '../../services/client-context.service';

@Component({
  selector: 'app-detail-enchere',
  imports: [CommonModule, OffreMontantComponent],
  templateUrl: './detail-enchere.component.html',
  styleUrl: './detail-enchere.component.css',
})
export class DetailEnchereComponent {
  enchereId!: string;
  enchere = signal<Enchere | null>(null);
  msgError= signal<string|null>(null);
  afficherModal: boolean = false;
  typeEnchereToDo: TypeEnchere = TypeEnchere.MANUELLE;

  constructor(private clientContext: ClientContextService,
              private enchereService: EncheresService,
              private route: ActivatedRoute,
              private location: Location) {

  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
      this.enchereId = params.get('idEnchere') ?? '-1';
      if (this.enchereId) {
        this.loadEnchere();
      }
    })
    console.log("client connecté dans detailEnchere : " + this.clientContext.clientSelectionne()?.id);
  }

  loadEnchere() {
    this.enchereService.getById('', this.enchereId).subscribe(
      enchere => {
        this.enchere.set(enchere);
        console.log('Load enchere done !');
      }
    );
  }



  surencherir(): void {
    this.typeEnchereToDo = TypeEnchere.MANUELLE;
    this.afficherModal = true;
  }

  autoEnchere(): void {
    this.typeEnchereToDo = TypeEnchere.AUTOMATIQUE;
    this.afficherModal = true;
  }

  fermerModal(): void {
    this.afficherModal = false;
  }

  traiterOffre(montant: number): void {
    console.log("Traitement de l'offre avec le montant : " + montant);
    this.msgError.set(null);
    const offre: OffreRequest = {
      clientId: this.clientContext.clientSelectionne()?.id ?? null,
      enchereId: this.enchereId ? Number(this.enchereId) : null,
      montant: montant
    }

    console.log("offre envoyée : " + JSON.stringify(offre) );

    if (this.typeEnchereToDo === TypeEnchere.MANUELLE) {
      const enchere$ = this.enchereService.deposerOffre('/offres/manuelle', offre);
      enchere$.subscribe({
        next: (e: Enchere) => {
          console.log(JSON.stringify(e));
          this.enchere.set(e);
        },
        error: (err) => {
          this.msgError.set(err.error?.message?? 'Une erreur est survenue');
        }
      }
      );

    } else if(this.typeEnchereToDo === TypeEnchere.AUTOMATIQUE) {
      const enchere$ = this.enchereService.deposerOffre('/offres/auto', offre)
      enchere$.subscribe({
        next: (e: Enchere) => {
          console.log(JSON.stringify(e));
          this.enchere.set(e);
        },
        error: (err) => {
          this.msgError.set(err.error?.message?? 'Une erreur est survenue');
        }
      });
    }
    this.fermerModal();
  }

  goBack(): void {
    this.location.back();
  }
}
