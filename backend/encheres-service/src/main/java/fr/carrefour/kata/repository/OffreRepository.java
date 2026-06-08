package fr.carrefour.kata.repository;

import fr.carrefour.kata.entity.Offre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OffreRepository extends JpaRepository<Offre,Long> {
}
