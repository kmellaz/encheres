package fr.carrefour.kata.conf;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration des règles CORS (Cross-Origin Resource Sharing) de l'application.
 *
 * <p>Cette configuration autorise les requêtes HTTP provenant de
 * l'application front-end exécutée sur {@code http://localhost:4200}.
 * Les méthodes GET, POST, PUT, DELETE et OPTIONS sont autorisées,
 * ainsi que l'ensemble des en-têtes HTTP. Les informations
 * d'authentification (cookies, en-têtes d'autorisation, etc.)
 * peuvent également être transmises.</p>
 *
 * @author koceila.mellaz
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
