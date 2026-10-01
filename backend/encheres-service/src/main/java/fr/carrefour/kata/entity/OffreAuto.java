package fr.carrefour.kata.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * Représente une Offre automatique dans le système d'enchères.
 * Une Offre automatique est soumise par le système au nom d'un client.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@Entity
@DiscriminatorValue("OFFRE_AUTO")
public class OffreAuto extends Offre {
    private BigDecimal montantAuto;
    private BigDecimal montantMax;
}
