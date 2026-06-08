package fr.carrefour.kata.exception;

public class EnchereInactiveException extends FonctionelleException {
    public EnchereInactiveException (String message) {
        super(FonctionelleException.ENCHERE_INACTIVE_CODE, message);
    }
}
