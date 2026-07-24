package fr.carrefour.kata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point d'entrée de l'application Spring Boot du service d'enchères.
 *
 * <p>Configure et démarre l'application avec les auto-configurations Spring Boot.
 * Expose les APIs REST pour la gestion des enchères, des clients et des offres.</p>
 *
 */
@SpringBootApplication
public class EncheresServiceApplication {

    /**
     * Méthode principale démarrant l'application Spring Boot.
     *
     * @param args arguments de la ligne de commande
     */
    public static void main(String[] args) {
        SpringApplication.run(EncheresServiceApplication.class, args);
    }
}
