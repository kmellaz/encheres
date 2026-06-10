package fr.carrefour.kata.repository;

import fr.carrefour.kata.entity.Enchere;
import fr.carrefour.kata.enums.StatutEnchere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnchereRepository extends JpaRepository<Enchere, Long> {

    @Query("select e from Enchere e where e.statut = :statut order by e.dateFin asc")
    List<Enchere> trouverEncheresActives(@Param("statut") StatutEnchere statut);
}
