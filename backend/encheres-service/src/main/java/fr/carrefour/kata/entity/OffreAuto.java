package fr.carrefour.kata.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@Entity
@DiscriminatorValue("OFFRE_AUTO")
public class OffreAuto extends Offre {
    private BigDecimal montantAuto;
    private BigDecimal montantMax;
}
