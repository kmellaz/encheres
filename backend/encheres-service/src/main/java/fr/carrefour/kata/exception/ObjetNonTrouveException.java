package fr.carrefour.kata.exception;

public class ObjetNonTrouveException extends FonctionelleException {
    public ObjetNonTrouveException (String message) {
       super(FonctionelleException.OBJET_NON_TROUVE_CODE, message);
    }
}
