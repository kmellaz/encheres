package fr.carrefour.kata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * DTO pour l'entité Client (transmis par l'API).
 *
 * Contient les champs exposés aux consommateurs de l'API.
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private List<OffreDto> offres;
}
