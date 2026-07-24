package fr.carrefour.kata.repository;

import fr.carrefour.kata.entity.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Repository JPA pour l'entité {@link fr.carrefour.kata.entity.Offre}.
 *
 * Fournit des méthodes de requête spécifiques pour les offres.
 */
public interface OffreRepository extends JpaRepository<Offre,Long> {
}
