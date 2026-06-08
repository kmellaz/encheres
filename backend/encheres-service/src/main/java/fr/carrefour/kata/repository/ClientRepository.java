package fr.carrefour.kata.repository;


import fr.carrefour.kata.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByEmail(String email);
    List<Client> findByNom(String nom);
    List<Client> findByPrenom(String prenom);
}
