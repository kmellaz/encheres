package fr.carrefour.kata.service;

import fr.carrefour.kata.dto.EnchereDto;
import fr.carrefour.kata.entity.*;
import fr.carrefour.kata.enums.StatutEnchere;
import fr.carrefour.kata.enums.TypeEnchere;
import fr.carrefour.kata.exception.*;
import fr.carrefour.kata.repository.ClientRepository;
import fr.carrefour.kata.repository.EnchereRepository;
import fr.carrefour.kata.repository.OffreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
 * Service pour la configuration d'offres automatiques par les clients.
 *
 * <p>Permet à un client de définir un plafond (montantMax) pour une enchère afin
 * que le système place automatiquement des offres au besoin.</p>
 *
 */
@Service
@RequiredArgsConstructor
@Transactional
public class OffreAutoService {

    private final OffreRepository offreRepository;
    private final ClientRepository clientRepository;
    private final EnchereRepository enchereRepository;

 /**
  * Crée une offre automatique pour un client sur une enchère donnée.
  *
  * <p>Vérifie l'existence et l'état de l'enchère et la validité du montantMax.</p>
  *
  * @param clientId   identifiant du client créant l'offre automatique
  * @param enchereId  identifiant de l'enchère
  * @param montantMax plafond que le système pourra atteindre pour ce client
  * @throws fr.carrefour.kata.exception.FonctionelleException exceptions métiers (client/enchère non trouvés,
  *         enchère inactive, montant incorrect, etc.)
  */

    @Transactional
    public EnchereDto creerOffre(Long clientId, Long enchereId, BigDecimal montantMax) throws FonctionelleException {
        Client client = this.clientRepository.findById(clientId).orElseThrow(() -> new ObjetNonTrouveException("Client n'est pas trouvé id: " + clientId));
        Enchere enchere = this.enchereRepository.findById(enchereId).orElseThrow(() -> new ObjetNonTrouveException("Enchere n'est pas trouvée id: " + enchereId));

        if (StatutEnchere.FINISHED.equals(enchere.getStatut())) {
            throw new EnchereInactiveException("Enchere est inactive id: " + enchereId);
        }
        if (montantMax == null || montantMax.compareTo(BigDecimal.ZERO) <= 0){
            throw new MontantIncorrectException("Le montant doit être supérieur à zéro id: " + enchereId);
        }
        if (montantMax.compareTo(enchere.getMontantCourant()) <= 0) {
            throw new MontantEnchereInsuffisant(montantMax, enchere.getMontantCourant(), "Le montant doit être supérieur au montant courant de l'enchère : > " + enchere.getMontantCourant());

        }

        Offre offre = OffreAuto.builder()
                .client(client)
                .enchere(enchere)
                .montantMax(montantMax)
                .dateCreation(LocalDateTime.now())
                .build();

        this.offreRepository.save(offre);
        enchere.setType(TypeEnchere.AUTOMATIQUE); // on passe le type de l'enchère à AUTO dès qu'une offre auto est créée

    return EnchereDto.builder()
                .id(enchere.getId())
                .description(enchere.getDescription())
                .montantInitial(enchere.getMontantInitial())
                .montantCourant(enchere.getMontantCourant())
                .dateDebut(enchere.getDateDebut())
                .dateFin(enchere.getDateFin())
                .statut(enchere.getStatut())
                .type(enchere.getType())
                .build();
    }
}
