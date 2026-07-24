package fr.carrefour.kata.response;

import java.time.Instant;
import java.util.Map;

/**
 * Représente les informations retournées lors d'une erreur API.
 *
 * @param timestamp date et heure de l'erreur
 * @param status code de statut HTTP
 * @param error libellé de l'erreur HTTP
 * @param message message décrivant l'erreur
 * @param details détails complémentaires de l'erreur sous forme de paires clé/valeur
 */
public record ApiEncheresError(Instant timestamp,
                               int status,
                               String error,
                               String message,
                               Map<String, String> details) {
}
