package fr.carrefour.kata.enums;

/**
 * Représente le type de l'enchère dans le système : MANUELLE ou AUTOMATIQUE.
 * <p>Une enchère de type MANUELLE est une enchère où les clients soumettent des offres manuellement.</p>
 * <p>Une enchère de type AUTOMATIQUE est une enchère où un client a défini une offre automatique avec un montant maximum,
 * pour laquelle le système surencherit automatiquement les autres clients jusqu'au montant maximum défini.</p>
 */
public enum TypeEnchere {
    MANUELLE,
    AUTOMATIQUE
}
