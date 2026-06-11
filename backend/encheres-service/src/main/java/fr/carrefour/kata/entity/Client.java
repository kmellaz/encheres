package fr.carrefour.kata.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

/**
 * Représente un client dans le système d'enchères.
 *
 * <p>Un client est une personne physique qui peut soumettre une ou plusieurs offres
 * sur une enchère.
 * Cette entité contient des informations qui peuvent l'identifier. </p>
 */

@Entity
@Table(name = "client")
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;

    @OneToMany (mappedBy = "client", cascade = CascadeType.ALL)
    private List<Offre> offres;
}
