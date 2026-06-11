package fr.carrefour.kata.exception;
/**
 * Exception métier levée lorsqu'un objet recherché n'est pas trouvé.
 *
 * <p>Utilise un code d'erreur métier défini dans {@link fr.carrefour.kata.exception.FonctionelleException}.</p>
 *
 */
public class ObjetNonTrouveException extends FonctionelleException {
    public ObjetNonTrouveException (String message) {
       super(FonctionelleException.OBJET_NON_TROUVE_CODE, message);
    }
}
