package fr.carrefour.kata.exception;
/**
 * Exception métier levée lorsqu'une opération est demandée avec un montant d'enchère incorrect (par exemple, un montant négatif ou nul).
 *
 * <p>Utilise un code d'erreur métier défini dans {@link fr.carrefour.kata.exception.FonctionelleException}.</p>
 *
 */
public class MontantIncorrectException extends FonctionelleException{
    public MontantIncorrectException(String message) {
        super(FonctionelleException.MONTANT_ENCHERE_INCORRECT_CODE, message);
    }
}
