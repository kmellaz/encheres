package fr.carrefour.kata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
/**
 * DTO pour l'entité OffreManuelle.
 *
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OffreManuelleDto extends OffreDto{
    private BigDecimal montant;
}
