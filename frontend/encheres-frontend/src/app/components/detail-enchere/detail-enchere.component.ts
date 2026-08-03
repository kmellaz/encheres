import {Component, OnInit, signal, ChangeDetectionStrategy} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {EncheresService} from '../../services/encheres.service';
import {Enchere} from '../../models/enchere';
import {CommonModule, Location} from '@angular/common';
import {EnchereStatus} from '../../models/enums/enchere-status.enum';
import {BidType} from '../../models/enums/bid-type.enum';
import {OffreMontantComponent} from '../offre-montant/offre-montant.component';
import {OffreRequest} from '../../models/offre-request';
import {ClientContextService} from '../../services/client-context.service';

@Component({
  selector: 'app-detail-enchere',
  imports: [CommonModule, OffreMontantComponent],
  templateUrl: './detail-enchere.component.html',
  styleUrl: './detail-enchere.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DetailEnchereComponent implements OnInit {
  enchereId!: string;
  enchere = signal<Enchere | null>(null);
  msgError = signal<string | null>(null);
  isLoading = signal<boolean>(false);
  afficherModal = signal<boolean>(false);
  typeEnchereToDo: BidType = BidType.MANUAL;
  isSubmittingOffer = signal<boolean>(false);

  EnchereStatus = EnchereStatus;
  BidType = BidType;

  constructor(
    private clientContext: ClientContextService,
    private enchereService: EncheresService,
    private route: ActivatedRoute,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.enchereId = params.get('idEnchere') ?? '-1';
      if (this.enchereId && this.enchereId !== '-1') {
        this.loadEnchere();
      }
    });
  }

  loadEnchere(): void {
    this.isLoading.set(true);
    this.msgError.set(null);

    this.enchereService.getById(this.enchereId)
      .subscribe({
        next: (enchere) => this.enchere.set(enchere),
        error: (err) => this.msgError.set('Erreur lors du chargement de l\'enchère'),
        complete: () => this.isLoading.set(false)
      });
  }

  surencherir(): void {
    this.typeEnchereToDo = BidType.MANUAL;
    this.afficherModal.set(true);
  }

  autoEnchere(): void {
    this.typeEnchereToDo = BidType.AUTOMATIC;
    this.afficherModal.set(true);
  }

  fermerModal(): void {
    this.afficherModal.set(false);
  }

  traiterOffre(montant: number): void {
    this.msgError.set(null);

    const offre: OffreRequest = {
      clientId: this.clientContext.clientSelectionne()?.id ?? null,
      enchereId: this.enchereId ? Number(this.enchereId) : null,
      montant: montant
    };

    this.isSubmittingOffer.set(true);

    const offreObservable = this.typeEnchereToDo === BidType.MANUAL
      ? this.enchereService.deposerOffreManuelle(offre)
      : this.enchereService.deposerOffreAutomatique(offre);

    offreObservable.subscribe({
      next: (enchere) => {
        this.enchere.set(enchere);
        this.fermerModal();
      },
      error: (err) => {
        this.msgError.set(err?.error?.message ?? 'Erreur lors de la soumission de l\'offre');
      },
      complete: () => this.isSubmittingOffer.set(false)
    });
  }

  goBack(): void {
    this.location.back();
  }
}
