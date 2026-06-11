package fr.carrefour.kata.entity;

import fr.carrefour.kata.enums.StatutEnchere;
import fr.carrefour.kata.enums.TypeEnchere;
import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Représente une Enchère dans le système d'enchères.
 * <p>Une enchère contient des informations qui pemettent de l'identitfier.
 * Une enchere peut contenir une ou plusieurs Offres clients</p>
 *
 */
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
    @Enumerated(EnumType.STRING)
    private StatutEnchere statut;

    @Enumerated(EnumType.STRING)
    private TypeEnchere type;

    @OneToMany(mappedBy = "enchere", cascade = CascadeType.ALL)
    private List<Offre> offres;
}
