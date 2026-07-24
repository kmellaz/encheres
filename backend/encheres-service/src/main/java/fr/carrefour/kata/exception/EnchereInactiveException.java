package fr.carrefour.kata.exception;

/**
 * Exception métier levée lorsqu'une opération est demandée sur une enchère inactive.
 *
 * <p>Utilise un code d'erreur métier défini dans {@link fr.carrefour.kata.exception.FonctionelleException}.</p>
 *
 */
public class EnchereInactiveException extends FonctionelleException {
    public EnchereInactiveException (String message) {
        super(FonctionelleException.ENCHERE_INACTIVE_CODE, message);
    }
}
