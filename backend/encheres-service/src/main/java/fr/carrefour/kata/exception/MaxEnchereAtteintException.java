package fr.carrefour.kata.exception;
/**
 * Exception métier levée lorsqu'une enchère atteint son montant maximum autorisé.
 *
 * <p>Utilise un code d'erreur métier défini dans {@link fr.carrefour.kata.exception.FonctionelleException}.</p>
 *
 */
public class MaxEnchereAtteintException extends FonctionelleException {
    public MaxEnchereAtteintException(String message) {
        super(FonctionelleException.MONTANT_MAX_ENCHERE_ATTEINT_CODE, message);
    }
}
