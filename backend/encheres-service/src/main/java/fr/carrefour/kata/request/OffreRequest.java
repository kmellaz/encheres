package fr.carrefour.kata.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OffreRequest(
        @NotNull
        Long clientId,
        @NotNull
        Long enchereId,
        @NotNull
        BigDecimal montant
) {}
