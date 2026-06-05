package fr.carrefour.kata.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private List<OffreDto> offres;
}
