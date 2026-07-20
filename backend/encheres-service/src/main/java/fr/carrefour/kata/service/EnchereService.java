package fr.carrefour.kata.service;

import fr.carrefour.kata.dto.ClientDto;
import fr.carrefour.kata.dto.EnchereDto;
import fr.carrefour.kata.dto.OffreAutoDto;
import fr.carrefour.kata.dto.OffreManuelleDto;
import fr.carrefour.kata.entity.Enchere;
import fr.carrefour.kata.entity.OffreAuto;
import fr.carrefour.kata.entity.OffreManuelle;
import fr.carrefour.kata.enums.StatutEnchere;
import fr.carrefour.kata.exception.FonctionelleException;
import fr.carrefour.kata.exception.ObjetNonTrouveException;
import fr.carrefour.kata.repository.EnchereRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service métier pour les opérations liées aux enchères.
 *
 * <p>Fournit les cas d'usage en lecture et transformation des entités vers des DTO
 * utilisables par les contrôleurs.</p>
 *
 */
@Service
@RequiredArgsConstructor

public class EnchereService {
    private final EnchereRepository enchereRepository;

    /**
     * Retourne une enchère identifiée par son id, convertie en {@link fr.carrefour.kata.dto.EnchereDto}.
     *
     * @param id l'identifiant de l'enchère recherchée (non null)
     * @return l'objet {@link fr.carrefour.kata.dto.EnchereDto} correspondant
     * @throws fr.carrefour.kata.exception.FonctionelleException si l'enchère n'existe pas
     */
    @Transactional(readOnly = true)
    public EnchereDto trouverEnchereParId(Long id) throws FonctionelleException {
        Enchere enchere = enchereRepository.findById(id)
                .orElseThrow(() -> new ObjetNonTrouveException("Enchere est introuvable id: " + id));
        return EnchereDto.builder()
                .id(enchere.getId())
                .description(enchere.getDescription())
                .dateDebut(enchere.getDateDebut())
                .dateFin(enchere.getDateFin())
                .montantInitial(enchere.getMontantInitial())
                .montantCourant(enchere.getMontantCourant())
                .statut(enchere.getStatut())
                .type(enchere.getType())
                .version(enchere.getVersion())
                .offres(enchere.getOffres().stream().map(offre ->
                        {
                            if (offre instanceof OffreManuelle offreManuelle) {
                                return OffreManuelleDto.builder()
                                        .id(offreManuelle.getId())
                                        .client(ClientDto.builder()
                                                .id(offreManuelle.getClient().getId())
                                                .nom(offreManuelle.getClient().getNom())
                                                .prenom(offreManuelle.getClient().getPrenom())
                                                .email(offreManuelle.getClient().getEmail())
                                                .build())
                                        .montant(offreManuelle.getMontant())
                                        .dateCreation(offreManuelle.getDateCreation())
                                        .build();
                            } else if (offre instanceof OffreAuto offreAuto) {
                                return OffreAutoDto.builder()
                                        .id(offreAuto.getId())
                                        .client(ClientDto.builder()
                                                .id(offreAuto.getClient().getId())
                                                .nom(offreAuto.getClient().getNom())
                                                .prenom(offreAuto.getClient().getPrenom())
                                                .email(offreAuto.getClient().getEmail())
                                                .build())
                                        .montantMax(offreAuto.getMontantMax())
                                        .montantAuto(offreAuto.getMontantAuto())
                                        .dateCreation(offreAuto.getDateCreation())
                                        .build();

                            } else {
                                return null;
                            }
                        }).toList()).build();
    }

   /**
    * Récupère la liste des enchères qui sont actives (statut ACTIVE).
    *
    * @return liste non null (éventuellement vide) de {@link fr.carrefour.kata.dto.EnchereDto}
    * @since 1.0
    */
   @Transactional(readOnly = true)
    public List<EnchereDto> trouverEncheresActives() {
        return enchereRepository.trouverEncheresActives(StatutEnchere.ACTIVE).stream()
                .map(enchere -> EnchereDto.builder()
                        .id(enchere.getId())
                        .description(enchere.getDescription())
                        .dateDebut(enchere.getDateDebut())
                        .dateFin(enchere.getDateFin())
                        .montantInitial(enchere.getMontantInitial())
                        .montantCourant(enchere.getMontantCourant())
                        .statut(enchere.getStatut())
                        .type(enchere.getType())
                        .version(enchere.getVersion()).build())
                .toList();
    }

    /**
     * Récupère la liste des enchères du SI.
     *
     * @return liste non null (éventuellement vide) de {@link fr.carrefour.kata.dto.EnchereDto}
     */
    @Transactional(readOnly = true)
    public List<EnchereDto> trouverEncheres() {
        return enchereRepository.findAll().stream()
                .map(enchere -> EnchereDto.builder()
                        .id(enchere.getId())
                        .description(enchere.getDescription())
                        .dateDebut(enchere.getDateDebut())
                        .dateFin(enchere.getDateFin())
                        .montantInitial(enchere.getMontantInitial())
                        .montantCourant(enchere.getMontantCourant())
                        .statut(enchere.getStatut())
                        .type(enchere.getType())
                        .version(enchere.getVersion()).build())
                .toList();
    }


}
