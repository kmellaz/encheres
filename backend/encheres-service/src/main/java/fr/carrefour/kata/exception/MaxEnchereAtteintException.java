package fr.carrefour.kata.exception;

public class MaxEnchereAtteintException extends FonctionelleException {
    public MaxEnchereAtteintException(String message) {
        super(FonctionelleException.MONTANT_MAX_ENCHERE_ATTEINT_CODE, message);
    }
}
