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

@RestControllerAdvice
public class GestionnaireExceptionGlobal {

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiEncheresError> handleValidation(
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
                "Erreur de validation des arguments de la methode",
                fieldErrors
        );

        return ResponseEntity.badRequest().body(error);
    }
}
