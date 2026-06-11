package fr.carrefour.kata.controler;

import fr.carrefour.kata.request.OffreRequest;
import fr.carrefour.kata.service.OffreAutoService;
import fr.carrefour.kata.service.OffreManuelleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("api/encheres/offres")
@RequiredArgsConstructor
public class OffreControleur {

    private final OffreAutoService offreAutoService;
    private final OffreManuelleService offreManuelleService;

    /**
     * Déclenche la création d'une offre automatique.
     *
     * @param request corps de la requête contenant clientId, enchereId et montant
     */
    @PostMapping("/auto")
    public void creerOffreAuto(@Valid @RequestBody OffreRequest request) {
        this.offreAutoService.creerOffre(request.clientId(), request.enchereId(), request.montant());
    }

    /**
     * Dépose une offre manuelle via POST.
     *
     * @param request corps de la requête contenant clientId, enchereId et montant
     * @since 1.0
     */
    @PostMapping("/manuelle")
    public void deposerOffreManuelle(@Valid @RequestBody OffreRequest request) {
        this.offreManuelleService.deposerOffre(request.clientId(), request.enchereId(), request.montant());
    }
}
