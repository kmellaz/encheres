package fr.carrefour.kata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
/**
 * DTO pour l'entité OffreAuto.
 *
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OffreAutoDto extends OffreDto{
    private BigDecimal montantAuto;
    private BigDecimal montantMax;
}
