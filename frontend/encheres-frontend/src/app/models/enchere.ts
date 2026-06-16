export interface Enchere {
  id: number;
  description: string;
  montantInitial: number;
  montantCourant: number;
  dateDebut : Date;
  dateFin : Date;
  statut: string;
  type: string;
}
