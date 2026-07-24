package fr.carrefour.kata.exception;

import java.math.BigDecimal;

/**
 * Exception métier levée lorsqu'une opération est demandée avec un montant d'enchère insuffisant par rapport au montant minimum requis.
 *
 * <p>Utilise un code d'erreur métier défini dans {@link fr.carrefour.kata.exception.FonctionelleException}.</p>
 *
 */

public class MontantEnchereInsuffisant extends FonctionelleException {
    private final BigDecimal montantPropose;
    private final BigDecimal montantMinimum;

    public MontantEnchereInsuffisant(BigDecimal montantPropose, BigDecimal montantMinimum, String message) {
        super(FonctionelleException.MONTANT_ENCHERE_INSUFFISANT_CODE, message);
        this.montantPropose = montantPropose;
        this.montantMinimum = montantMinimum;
    }
}
