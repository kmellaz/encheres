package fr.carrefour.kata.exception;

import fr.carrefour.kata.response.ApiEncheresError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;
/**
 * Gère et centralise les erreurs de validation des arguments de requête et les exceptions métiers levées.
 *
 * <p>Capture les violations de contraintes (ex. @NotNull, @Valid) et retourne
 * un détail complet des champs en erreur avec leurs messages.</p>
 */

@RestControllerAdvice
public class GestionnaireExceptionGlobal {
    /**
     * Gère l'exception levée lors d'une opération sur une enchère inactive.
     *
     * @param ex exception {@link EnchereInactiveException} capturée
     * @return ResponseEntity avec statut HTTP 409 CONFLICT et détails de l'erreur
     */

    @ExceptionHandler(EnchereInactiveException.class)
    public ResponseEntity<ApiEncheresError> gererEnchereInactiveException(EnchereInactiveException ex) {
        ApiEncheresError error = new ApiEncheresError(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error);
    }

    /**
     * Gère l'exception levée lorsque le montant maximum d'une enchère automatique est atteint.
     *
     * @param ex exception {@link MaxEnchereAtteintException} capturée
     * @return ResponseEntity avec statut HTTP 409 CONFLICT et détails de l'erreur
     */

    @ExceptionHandler(MaxEnchereAtteintException.class)
    public ResponseEntity<ApiEncheresError> gererMaxEnchereAtteintException(MaxEnchereAtteintException ex) {
        ApiEncheresError error = new ApiEncheresError(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error);
    }

    /**
     * Gère l'exception levée lorsque le montant proposé est insuffisant.
     *
     * @param ex exception {@link MontantEnchereInsuffisant} capturée
     * @return ResponseEntity avec statut HTTP 409 CONFLICT et détails de l'erreur
     * @since 1.0
     */

    @ExceptionHandler(MontantEnchereInsuffisant.class)
    public ResponseEntity<ApiEncheresError> gererMontantEnchereInsuffisantException(MontantEnchereInsuffisant ex) {
        ApiEncheresError error = new ApiEncheresError(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error);
    }

    /**
     * Gère l'exception levée lorsque le montant proposé est invalide (négatif ou zéro).
     *
     * @param ex exception {@link MontantIncorrectException} capturée
     * @return ResponseEntity avec statut HTTP 409 CONFLICT et détails de l'erreur
     */

    @ExceptionHandler(MontantIncorrectException.class)
    public ResponseEntity<ApiEncheresError> gererMontantIncorrectException(MontantIncorrectException ex) {
        ApiEncheresError error = new ApiEncheresError(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error);
    }

    /**
     * Gère l'exception levée lorsqu'une entité (client, enchère, offre) est introuvable.
     *
     * @param ex exception {@link ObjetNonTrouveException} capturée
     * @return ResponseEntity avec statut HTTP 404 NOT_FOUND et détails de l'erreur
     * @since 1.0
     */

    @ExceptionHandler(ObjetNonTrouveException.class)
    public ResponseEntity<ApiEncheresError> gererObjetNonTrouveException(ObjetNonTrouveException ex) {
        ApiEncheresError error = new ApiEncheresError(
                Instant.now(),
                HttpStatus.NOT_FOUND.value(),
                "NON_TROUVE",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    /**
     * Gère l'exception levée lorsqu'on a un accès concurrentiel sur une enchère
     *
     * @param ex exception {@link AccesConcurrentException} capturée
     * @return ResponseEntity avec statut HTTP 409 CONFLICT et détails de l'erreur
     * @since 1.0
     */

    @ExceptionHandler(AccesConcurrentException.class)
    public ResponseEntity<ApiEncheresError> gererAccesConcurrentException(AccesConcurrentException ex) {
        ApiEncheresError error = new ApiEncheresError(
                Instant.now(),
                HttpStatus.CONFLICT.value(),
                "CONFLICT",
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(error);
    }

    /**
     * Gère les erreurs de validation des arguments de requête.
     *
     * <p>Capture les violations de contraintes (ex. @NotNull, @Valid) et retourne
     * un détail complet des champs en erreur avec leurs messages.</p>
     *
     * @param ex exception {@link MethodArgumentNotValidException} capturée
     * @return ResponseEntity avec statut HTTP 400 BAD_REQUEST, code erreur ERREUR_VALIDATION,
     *         et une map des champs erronés avec leurs messages
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiEncheresError> gererValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage,
                        (a, b) -> a
                ));

        ApiEncheresError error = new ApiEncheresError(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "ERREUR_VALIDATION",
                "Erreur de validation des arguments de la méthode",
                fieldErrors
        );

        return ResponseEntity.badRequest().body(error);
    }


}
