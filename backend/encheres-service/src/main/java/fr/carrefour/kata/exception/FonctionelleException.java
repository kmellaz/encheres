package fr.carrefour.kata.exception;

public abstract class FonctionelleException extends RuntimeException {

    public static final int OBJET_NON_TROUVE_CODE = 100;
    public static final int ENCHERE_INACTIVE_CODE = 101;
    public static final int MONTANT_ENCHERE_INCORRECT_CODE = 102;
    public static final int MONTANT_ENCHERE_INSUFFISANT_CODE = 103;
    public static final int MONTANT_MAX_ENCHERE_ATTEINT_CODE = 104;

    private final int code;


    public FonctionelleException(int code, String message) {
        super(message);
        this.code = code;

    }
}
