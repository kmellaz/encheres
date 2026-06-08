package fr.carrefour.kata.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
public class OffreAutoDto extends OffreDto{
    private BigDecimal montantAuto;
    private BigDecimal montantMax;
}
