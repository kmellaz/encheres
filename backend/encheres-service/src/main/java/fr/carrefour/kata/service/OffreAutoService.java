package fr.carrefour.kata.service;

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

@Service
@RequiredArgsConstructor
@Transactional
public class OffreAutoService {

    private final OffreRepository offreRepository;
    private final ClientRepository clientRepository;
    private final EnchereRepository enchereRepository;

    /**
     * le client configure une offre automatique avec un montant maximum.
     *
     * @param clientId
     * @param enchereId
     * @param montantMax
     */
    public void creerOffre(Long clientId, Long enchereId, BigDecimal montantMax) throws FonctionelleException {
        Client client = this.clientRepository.findById(clientId).orElseThrow(() -> new ObjetNonTrouveException("Client n'est pas trouvé id: " + clientId));
        Enchere enchere = this.enchereRepository.findById(enchereId).orElseThrow(() -> new ObjetNonTrouveException("Enchere n'est pas trouvée id: " + enchereId));

        if (StatutEnchere.FINISHED.equals(enchere.getStatut())) {
            throw new EnchereInactiveException("Enchere est inactive id: " + enchereId);
        }
        if (montantMax == null || montantMax.compareTo(BigDecimal.ZERO) <= 0){
            throw new MontantIncorrectException("Le montant doit être supérieur à zéro id: " + enchereId);
        }
        if (montantMax.compareTo(enchere.getMontantCourant()) <= 0) {
            throw new MontantEnchereInsuffisant(montantMax, enchere.getMontantCourant(), "Le montant doit être supérieur au montant courant de l'enchère id: " + enchereId);

        }

        Offre offre = OffreAuto.builder()
                .client(client)
                .enchere(enchere)
                .montantMax(montantMax)
                .dateCreation(LocalDateTime.now())
                .build();

        this.offreRepository.save(offre);
        enchere.setType(TypeEnchere.AUTOMATIQUE); // on passe le type de l'enchère à AUTO dès qu'une offre auto est créée


    }
}
