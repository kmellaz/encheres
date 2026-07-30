package fr.carrefour.kata.controler;

import fr.carrefour.kata.dto.ClientDto;
import fr.carrefour.kata.exception.FonctionelleException;
import fr.carrefour.kata.service.ClientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
/**
 * Contrôleur REST pour les opérations liées aux clients.
 *
 * Expose notamment l'endpoint :
 * - GET /api/encheres/clients : récupérer la liste des clients
 *
 */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("api/encheres/clients")
@RequiredArgsConstructor
public class ClientControleur {

    private final ClientService clientService;

    /**
     * Endpoint GET qui retourne la liste des clients (DTO).
     *
     * @return ResponseEntity contenant la liste des {@link fr.carrefour.kata.dto.ClientDto}
     * @since 1.0
     */
    @GetMapping
    public ResponseEntity<List<ClientDto>> trouverClients() {
        return ResponseEntity.status(HttpStatus.OK).body(this.clientService.trouverClients());
    }

    /**
     * Endpoint GET qui retourne le client (DTO) par son identifiant.
     * @param id
     * @return ResponseEntity contenant le {@link fr.carrefour.kata.dto.ClientDto} correspondant à l'identifiant fourni
     * @throws FonctionelleException si le client n'est pas trouvé
     */
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> trouverClientParId(@NotNull  @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.clientService.trouverClientParId(id));
    }

}
