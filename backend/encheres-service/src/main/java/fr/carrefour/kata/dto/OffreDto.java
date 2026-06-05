package fr.carrefour.kata.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
public abstract class OffreDto {
    private Long id;
    private LocalDateTime dateCreation;
    private ClientDto client;
    private EnchereDto enchere;
}
