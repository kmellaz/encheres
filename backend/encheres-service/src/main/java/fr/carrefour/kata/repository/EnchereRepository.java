package fr.carrefour.kata.repository;

import fr.carrefour.kata.entity.Enchere;
import fr.carrefour.kata.enums.StatutEnchere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
/**
 * Repository JPA pour l'entité {@link fr.carrefour.kata.entity.Enchere}.
 *
 * Fournit des méthodes de requête spécifiques pour les enchères.
 */
public interface EnchereRepository extends JpaRepository<Enchere, Long> {

    /**
     * Récupère les enchères avec le statut fourni triées par date de fin croissante.
     *
     * @param statut statut à filtrer (ex. {@link fr.carrefour.kata.enums.StatutEnchere#ACTIVE})
     * @return liste d'{@link fr.carrefour.kata.entity.Enchere}
     */

    @Query("select e from Enchere e where e.statut = :statut order by e.dateFin asc")
    List<Enchere> trouverEncheresActives(@Param("statut") StatutEnchere statut);
}
