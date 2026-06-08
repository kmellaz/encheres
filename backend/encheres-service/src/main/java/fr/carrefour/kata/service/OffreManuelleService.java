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
public class OffreManuelleService {

    private final OffreRepository offreRepository;
    private final EnchereRepository enchereRepository;
    private final ClientRepository clientRepository;

    /**
     * Le client soumet une offre avec un montant pour une enchère donnée.
     * si le type de l'enchere cible est automatique Le système surencherit automatiquement pour le client qui a configuré l'enchere
     * en augmentant le montant d'un pas de 1 euro jusqu'à atteindre le maximum configuré
     *
     * @param clientId
     * @param enchereId
     * @param montant
     */
    public void deposerOffre(Long clientId, Long enchereId, BigDecimal montant) throws FonctionelleException {
        Client client = this.clientRepository.findById(clientId).orElseThrow(() -> new ObjetNonTrouveException("Client n'est pas trouvé id: " + clientId));
        Enchere enchere = this.enchereRepository.findById(enchereId).orElseThrow(() -> new ObjetNonTrouveException("Enchere n'est pas trouvée id: " + enchereId));

        if (StatutEnchere.FINISHED.equals(enchere.getStatut())
            || enchere.getDateFin().isBefore(LocalDateTime.now())) {
            throw new EnchereInactiveException("Enchere est inactive id: " + enchereId);
        }

        if (montant == null || montant.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontantIncorrectException("Le montant doit être supérieur à zéro id: " + enchereId);
        }
        if (montant.compareTo(enchere.getMontantCourant()) <= 0) {
            throw new MontantEnchereInsuffisant(montant, enchere.getMontantCourant(), "Le montant doit être supérieur au montant courant de l'enchère id: " + enchereId);
        }

        Offre offre = OffreManuelle.builder()
                .client(client)
                .enchere(enchere)
                .montant(montant)
                .dateCreation(LocalDateTime.now())
                .build();

        this.offreRepository.save(offre);
        enchere.setMontantCourant(montant);


        if (TypeEnchere.AUTOMATIQUE.equals(enchere.getType())) {
            this.surencherirAutomatiquement(enchere);
        }
    }

    private void surencherirAutomatiquement(Enchere enchere) {
        if (!TypeEnchere.AUTOMATIQUE.equals(enchere.getType())) {
            throw new IllegalStateException("l'enchère doit être de type automatique pour pouvoir surencherir automatiquement id: " + enchere.getId());
        }

        // récuperer l'offre de configuration de l'enchère auto
        Offre offreAuto = enchere.getOffres().stream()
                .filter(o -> o instanceof OffreAuto)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Il n'y a pas d'offre automatique configurée pour cette enchère id: " + enchere.getId()));

        Client client = offreAuto.getClient();
        BigDecimal montantMax = ((OffreAuto)offreAuto).getMontantMax();
        BigDecimal montantAuto = enchere.getMontantCourant().add(BigDecimal.ONE);

        if (montantMax.compareTo(montantAuto) > 0) {
            Offre nouvelleOffreAuto = OffreAuto.builder()
                    .client(client)
                    .enchere(enchere)
                    .montantAuto(montantAuto)
                    .dateCreation(LocalDateTime.now())
                    .build();
            this.offreRepository.save(nouvelleOffreAuto);
            enchere.setMontantCourant(montantAuto);

        } else {
            enchere.setStatut(StatutEnchere.FINISHED);
            throw new MaxEnchereAtteintException("Le montant maximum de l'enchère automatique a été atteint : " + montantMax);
        }


    }
}
