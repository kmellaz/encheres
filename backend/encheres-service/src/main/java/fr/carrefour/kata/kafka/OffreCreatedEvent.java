package fr.carrefour.kata.kafka;

import java.math.BigDecimal;

public record OffreCreatedEvent(Long offreId, Long enchereId, Long clientId, String email, BigDecimal montant) {
}
