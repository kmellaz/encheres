package fr.carrefour.kata.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
/**
 * Requête (record) pour la création d'une offre (manuelle ou automatique).
 *
 * <p>Utilisé pour transmettre les paramètres nécessaires d'une offre via les endpoints POST de l'API.</p>
 *
 */

public record OffreRequest(
        @NotNull
        Long clientId,
        @NotNull
        Long enchereId,
        @NotNull
        BigDecimal montant
) {}
