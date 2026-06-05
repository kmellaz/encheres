package fr.carrefour.kata.dto;

import fr.carrefour.kata.enums.StatutEnchere;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class EnchereDto {
    private Long id;
    private String description;
    private BigDecimal montantInitial;
    private BigDecimal montantCourant;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private StatutEnchere statut;
    private List<OffreDto> offres;
}
