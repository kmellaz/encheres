import {Component, EventEmitter, Input, Output, signal} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {BidType} from '../../models/enums/bid-type.enum';
import {CommonModule} from '@angular/common';

@Component({
  selector: 'app-offre-montant',
  imports: [FormsModule, CommonModule],
  templateUrl: './offre-montant.component.html',
  styleUrl: './offre-montant.component.css',
})
export class OffreMontantComponent {
  montant = signal<number | null>(null);
  errorMessage = signal<string | null>(null);

  @Output() fermer = new EventEmitter<void>();
  @Output() validerMontant = new EventEmitter<number>();

  @Input() typeEnchere!: BidType;
  @Input() isSubmitting: boolean = false;

  annuler(): void {
    this.fermer.emit();
    this.resetForm();
  }

  valider(): void {
    this.errorMessage.set(null);

    if (this.montant() === null || this.montant() === undefined) {
      this.errorMessage.set('Veuillez saisir un montant');
      return;
    }

    if (this.montant()! <= 0) {
      this.errorMessage.set('Le montant doit être positif');
      return;
    }

    this.validerMontant.emit(this.montant()!);
    this.resetForm();
  }

  private resetForm(): void {
    this.montant.set(null);
    this.errorMessage.set(null);
  }

  isManuelle(): boolean {
    return this.typeEnchere === BidType.MANUAL;
  }

  isAutomatique(): boolean {
    return this.typeEnchere === BidType.AUTOMATIC;
  }
}
