package fr.carrefour.kata.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
/**
 * Représente une Offre manuelle dans le système d'enchères.
 * Une Offre manuelle est soumise par un client.
 */

@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@DiscriminatorValue("OFFRE_MANUELLE")
public class OffreManuelle extends Offre {
    private BigDecimal montant;
}