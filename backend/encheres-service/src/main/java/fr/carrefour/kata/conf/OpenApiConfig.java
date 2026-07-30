package fr.carrefour.kata.conf;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration OpenAPI/Swagger pour l'API d'enchères.
 *
 * <p>Définit les métadonnées exposées par Springdoc-OpenAPI (titre, version, description)
 * qui seront visibles dans la documentation interactive Swagger UI et ReDoc.</p>
 *
 */
@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {

    /**
     * Crée et retourne la configuration OpenAPI personnalisée.
     *
     * <p>Produit un bean {@link io.swagger.v3.oas.models.OpenAPI} configuré avec
     * les informations de l'API (titre, version, description) affichées dans la
     * documentation Swagger.</p>
     *
     * @return configuration {@link io.swagger.v3.oas.models.OpenAPI} pour l'API
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Enchères API")
                        .version("v1")
                        .description("API de gestion des enchères"));
    }
}
