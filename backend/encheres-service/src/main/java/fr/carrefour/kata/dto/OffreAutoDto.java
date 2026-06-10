package fr.carrefour.kata.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class OffreAutoDto extends OffreDto{
    private BigDecimal montantAuto;
    private BigDecimal montantMax;
}
