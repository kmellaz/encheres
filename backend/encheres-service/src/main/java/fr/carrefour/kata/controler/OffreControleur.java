package fr.carrefour.kata.controler;

import fr.carrefour.kata.dto.EnchereDto;
import fr.carrefour.kata.request.OffreRequest;
import fr.carrefour.kata.service.OffreAutoService;
import fr.carrefour.kata.service.OffreManuelleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * Contrôleur REST gérant la création des offres (manuelle et automatique).
 *
 * Endpoints :
 * - POST /api/encheres/offres/auto : creation d'une offre automatique
 * - POST /api/encheres/offres/manuelle : dépôt d'une offre manuelle
 *
 */
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("api/encheres/offres")
@RequiredArgsConstructor
public class OffreControleur {

    private final OffreAutoService offreAutoService;
    private final OffreManuelleService offreManuelleService;

    /**
     * Création d'une offre automatique via POST.
     *
     * @param request corps de la requête contenant clientId, enchereId et montant
     * @return ResponseEntity contenant enchereDto {@link fr.carrefour.kata.dto.EnchereDto}
     */
    @PostMapping("/auto")
    public ResponseEntity<EnchereDto> creerOffreAuto(@Valid @RequestBody OffreRequest request) {
        EnchereDto enchereDto =  this.offreAutoService.creerOffre(request.clientId(), request.enchereId(), request.montant());
        return  ResponseEntity.status(HttpStatus.OK).body(enchereDto);
    }

    /**
     * Dépose une offre manuelle via POST.
     *
     * @param request corps de la requête contenant clientId, enchereId et montant
     * @return ResponseEntity contenant enchereDto {@link fr.carrefour.kata.dto.EnchereDto}
     */
    @PostMapping("/manuelle")
    public ResponseEntity<EnchereDto> deposerOffreManuelle(@Valid @RequestBody OffreRequest request) {
        EnchereDto enchereDto = this.offreManuelleService.deposerOffre(request.clientId(), request.enchereId(), request.montant());
        return ResponseEntity.status(HttpStatus.OK).body(enchereDto);
    }
}
