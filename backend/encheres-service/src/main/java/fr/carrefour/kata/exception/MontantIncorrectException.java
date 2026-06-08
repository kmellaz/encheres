package fr.carrefour.kata.exception;

public class MontantIncorrectException extends FonctionelleException{
    public MontantIncorrectException(String message) {
        super(FonctionelleException.MONTANT_ENCHERE_INCORRECT_CODE, message);
    }
}
