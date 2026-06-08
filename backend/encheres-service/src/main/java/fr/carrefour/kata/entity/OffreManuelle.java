package fr.carrefour.kata.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Data
@SuperBuilder
@Entity
@DiscriminatorValue("OFFRE_MANUELLE")
public class OffreManuelle extends Offre {
    private BigDecimal montant;
}