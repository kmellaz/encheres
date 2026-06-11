package fr.carrefour.kata.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 *  Représente une Offre dans le système d'enchères.
 *  <p>Une Offre centralise les informations communes à tous les types d'offres (manuelle ou automatique).
 *  Une Offre est rattachée à une Enchère et un Client. </p>
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE_OFFRE")
@Table(name = "offre")
@Data
@SuperBuilder
@NoArgsConstructor
public abstract class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected LocalDateTime dateCreation;

    @ManyToOne
    @JoinColumn(name = "client_id")
    protected Client client;

    @ManyToOne
    @JoinColumn(name = "enchere_id")
    protected Enchere enchere;

}
