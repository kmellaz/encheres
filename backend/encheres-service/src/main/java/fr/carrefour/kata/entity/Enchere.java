package fr.carrefour.kata.entity;

import fr.carrefour.kata.enums.StatutEnchere;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "enchere")
@Data
public class Enchere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private BigDecimal montantInitial;
    private BigDecimal montantCourant;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private StatutEnchere statut;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Offre> offres;
}
