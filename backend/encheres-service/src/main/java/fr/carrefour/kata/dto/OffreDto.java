package fr.carrefour.kata.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
/**
 * DTO pour l'entité Offre.
 *
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OffreManuelleDto.class, name = "MANUELLE"),
        @JsonSubTypes.Type(value = OffreAutoDto.class, name = "AUTOMATIQUE")
})

public abstract class OffreDto {
    protected Long id;
    protected LocalDateTime dateCreation;
    protected ClientDto client;
    protected EnchereDto enchere;
}
