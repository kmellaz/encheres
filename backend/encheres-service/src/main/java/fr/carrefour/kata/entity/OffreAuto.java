package fr.carrefour.kata.entity;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OffreAuto extends Offre {
    private BigDecimal montantMax;
}
