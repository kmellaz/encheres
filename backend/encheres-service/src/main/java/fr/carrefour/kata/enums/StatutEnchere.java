package fr.carrefour.kata.enums;

/**
 * Represente le statut d'une enchère : ACTIVE ou FINISHED
 * <p>Une enchère est considérée comme ACTIVE tant que sa date de fin n'est pas atteinte et le montant maximum défini pour une enchère automatique n'est pas atteint.
 * Une fois la date de fin ou le montant maximum est atteint (dans le cas d'une enchère automatique), le statut de l'enchère passe à FINISHED.</p>
 */
public enum StatutEnchere {
    ACTIVE,
    FINISHED
}
