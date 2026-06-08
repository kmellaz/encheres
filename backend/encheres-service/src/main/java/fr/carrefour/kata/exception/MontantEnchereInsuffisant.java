package fr.carrefour.kata.exception;

import java.math.BigDecimal;

public class MontantEnchereInsuffisant extends FonctionelleException {
    private BigDecimal montantPropose;
    private BigDecimal montantMinimum;

    public MontantEnchereInsuffisant(BigDecimal montantPropose, BigDecimal montantMinimum, String message) {
        super(FonctionelleException.MONTANT_ENCHERE_INSUFFISANT_CODE, message);
        this.montantPropose = montantPropose;
        this.montantMinimum = montantMinimum;
    }
}
