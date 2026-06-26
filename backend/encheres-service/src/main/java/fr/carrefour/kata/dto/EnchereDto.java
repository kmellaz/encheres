package fr.carrefour.kata.dto;

import fr.carrefour.kata.enums.StatutEnchere;
import fr.carrefour.kata.enums.TypeEnchere;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO pour l'entité Enchere (transmis par l'API).
 *
 * Contient les champs exposés aux consommateurs de l'API.
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnchereDto {
    private Long id;
    private String description;
    private BigDecimal montantInitial;
    private BigDecimal montantCourant;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private StatutEnchere statut;
    private TypeEnchere type;
    private List<OffreDto> offres;
    private Long version;
}
