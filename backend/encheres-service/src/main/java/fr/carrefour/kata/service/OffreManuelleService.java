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
 * Service gérant les offres manuelles (soumission client).
 *
 * <p>Valide le dépôt d'une offre manuelle et met à jour l'enchère courante.
 * Gère également la logique de surenchère automatique si l'enchère est de type automatique.</p>
 *
 */
@Service
@RequiredArgsConstructor

public class OffreManuelleService {

    private final OffreRepository offreRepository;
    private final EnchereRepository enchereRepository;
    private final ClientRepository clientRepository;

/**
 * Dépose une offre manuelle pour une enchère donnée.
 *
 * <p>Vérifie l'existence du client et de l'enchère, la validité du montant,
 * et met à jour le montant courant de l'enchère. Si l'enchère est de type
 * automatique, déclenche la surenchère automatique.</p>
 *
 * @param clientId  identifiant du client déposant l'offre
 * @param enchereId identifiant de l'enchère ciblée
 * @param montant   montant proposé (doit être > montant courant)
 * @throws fr.carrefour.kata.exception.FonctionelleException exceptions métiers (client/enchère non trouvés,
 *         enchère inactive, montant insuffisant ou incorrect, etc.)
 */
    @Transactional(noRollbackFor = MaxEnchereAtteintException.class)
    public EnchereDto deposerOffre(Long clientId, Long enchereId, BigDecimal montant) throws FonctionelleException {
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
            throw new MontantEnchereInsuffisant(montant, enchere.getMontantCourant(), "Le montant doit être supérieur au montant courant de l'enchère : > " + enchere.getMontantCourant());
        }

        if (TypeEnchere.AUTOMATIQUE.equals(enchere.getType()) ) {
            // récuperer l'offre de configuration de l'enchère auto
            Offre offreAuto = enchere.getOffres().stream()
                    .filter(o -> o instanceof OffreAuto)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("Il n'y a pas d'offre automatique configurée pour cette enchère id: " + enchere.getId()));
            BigDecimal montantMax = ((OffreAuto)offreAuto).getMontantMax();
            if (montant.compareTo(montantMax) > 0) {
                throw new MaxEnchereAtteintException("Le montant ne peut être supérieur au montant maximum défini : " + montant + " > " + montantMax);
            }
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

/**
 * Effectue une surenchère automatique sur une enchère configurée.
 *
 * <p>Recherche l'offre de configuration automatique (OffreAuto), calcule le nouveau montant
 * et crée une OffreAuto supplémentaire si le montant max n'est pas atteint.
 * Si le maximum est atteint, met l'enchère en statut FINISHED.</p>
 *
 * @param enchere enchère sur laquelle exécuter la surenchère
 * @throws IllegalStateException si l'enchère n'est pas de type automatique ou si la configuration est absente
 * FonctionelleException si le montant maximum de l'enchère automatique a été atteint
 */
    private void surencherirAutomatiquement(Enchere enchere) throws FonctionelleException {
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

        if (montantMax.compareTo(montantAuto) >= 0) {
            Offre nouvelleOffreAuto = OffreAuto.builder()
                    .client(client)
                    .enchere(enchere)
                    .montantAuto(montantAuto)
                    .dateCreation(LocalDateTime.now())
                    .build();
            this.offreRepository.save(nouvelleOffreAuto);
            enchere.setMontantCourant(montantAuto);
            if (montantMax.compareTo(montantAuto) == 0) {
                enchere.setStatut(StatutEnchere.FINISHED);
            }

        } else {
            enchere.setStatut(StatutEnchere.FINISHED);
            throw new MaxEnchereAtteintException("Le montant maximum de l'enchère automatique a été atteint : " + montantMax);
        }
    }
}
