package fr.carrefour.kata.service;

import fr.carrefour.kata.dto.ClientDto;
import fr.carrefour.kata.dto.EnchereDto;
import fr.carrefour.kata.dto.OffreAutoDto;
import fr.carrefour.kata.dto.OffreManuelleDto;
import fr.carrefour.kata.entity.Client;
import fr.carrefour.kata.entity.OffreAuto;
import fr.carrefour.kata.entity.OffreManuelle;
import fr.carrefour.kata.exception.FonctionelleException;
import fr.carrefour.kata.exception.ObjetNonTrouveException;
import fr.carrefour.kata.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
/**
 * Service métier pour la gestion des clients.
 *
 * <p>Expose les opérations de lecture (DTO) pour l'API : récupération de la liste des clients
 * avec leurs offres associées converties en DTO.</p>
 *
 */
@Service
@RequiredArgsConstructor

public class ClientService {
    private final ClientRepository clientRepository;

   /**
    * Récupère tous les clients du système et les mappe en {@link fr.carrefour.kata.dto.ClientDto}.
    *
    * @return liste de {@link fr.carrefour.kata.dto.ClientDto}, jamais null (peut être vide)
    */
   @Transactional(readOnly = true)
    public List<ClientDto> trouverClients() {
        return this.clientRepository.findAll().stream()
                .map(client -> ClientDto.builder()
                        .id(client.getId())
                        .nom(client.getNom())
                        .prenom(client.getPrenom())
                        .email(client.getEmail())
                        .offres(client.getOffres().stream().map(offre -> {
                            if (offre instanceof OffreManuelle offreManuelle) {
                                return OffreManuelleDto.builder()
                                        .id(offreManuelle.getId())
                                        .montant(offreManuelle.getMontant())
                                        .dateCreation(offreManuelle.getDateCreation())
                                        .enchere(EnchereDto.builder()
                                                .id(offreManuelle.getEnchere().getId())
                                                .description(offreManuelle.getEnchere().getDescription())
                                                .montantCourant(offreManuelle.getEnchere().getMontantCourant())
                                                .dateDebut(offreManuelle.getEnchere().getDateDebut())
                                                .dateFin(offreManuelle.getEnchere().getDateFin())
                                                .build())
                                        .build();
                            } else if (offre instanceof OffreAuto offreAuto) {
                                return OffreAutoDto.builder()
                                        .id(offreAuto.getId())
                                        .montantMax(offreAuto.getMontantMax())
                                        .montantAuto(offreAuto.getMontantAuto())
                                        .dateCreation(offreAuto.getDateCreation())
                                        .enchere(EnchereDto.builder()
                                                .id(offreAuto.getEnchere().getId())
                                                .description(offreAuto.getEnchere().getDescription())
                                                .montantCourant(offreAuto.getEnchere().getMontantCourant())
                                                .dateDebut(offreAuto.getEnchere().getDateDebut())
                                                .dateFin(offreAuto.getEnchere().getDateFin())
                                                .build())
                                        .build();
                            }
                            return null;
                        }).toList())
                        .build())
                .toList();
    }

    /**
     * Récupère le client par son ID, convertie en {@link fr.carrefour.kata.dto.ClientDto}.
     * @param idClient
     * @return
     * @throws FonctionelleException
     */
    @Transactional(readOnly = true)
    public ClientDto trouverClientParId(Long idClient) throws FonctionelleException {
        Client client = this.clientRepository.findById(idClient).orElseThrow(() -> new ObjetNonTrouveException("le client est introuvable id: " + idClient));
        return fr.carrefour.kata.dto.ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .email(client.getEmail()).build();
    }
}
