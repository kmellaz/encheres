import {Component, EventEmitter, Input, Output} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {TypeEnchere} from '../../models/enums/type-enchere.enum';

@Component({
  selector: 'app-offre-montant',
  imports: [FormsModule],
  templateUrl: './offre-montant.component.html',
  styleUrl: './offre-montant.component.css',
})
export class OffreMontantComponent {
  montant: number | null = null;

  @Output()
  fermer = new EventEmitter<void>();

  @Output()
  validerMontant = new EventEmitter<number>();

  @Input()
  typeEnchere!: TypeEnchere;


  annuler(): void{
    this.fermer.emit();
  }

  valider(): void {
    if(this.montant !== null && this.montant > 0){
      this.validerMontant.emit(this.montant);
    }
  }

  isAutomatique(): boolean{
    return this.typeEnchere === TypeEnchere.AUTOMATIQUE;
  }

  isManuelle(): boolean{
    return this.typeEnchere === TypeEnchere.MANUELLE;
  }

}
