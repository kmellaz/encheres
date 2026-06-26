package fr.carrefour.kata.exception;


/**
 * Exception métier levée lorsqu'une offre est soumise sur une enchère dont le montant est modifié par un autre client au même temps.
 *
 * <p>Utilise un code d'erreur métier défini dans {@link fr.carrefour.kata.exception.FonctionelleException}.</p>
 *
 */
public class AccesConcurrentException extends FonctionelleException{
    public AccesConcurrentException(String message) {
        super(ACCES_CONCURRENT_ENCHERE_CODE, message);
    }
}
