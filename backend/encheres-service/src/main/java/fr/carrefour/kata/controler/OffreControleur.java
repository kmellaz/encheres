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

@RestController
@RequestMapping("api/encheres/offres")
@RequiredArgsConstructor
public class OffreControleur {

    private final OffreAutoService offreAutoService;
    private final OffreManuelleService offreManuelleService;

    @PostMapping("/auto")
    public void creerOffreAuto(@Valid @RequestBody OffreRequest request) {
        this.offreAutoService.creerOffre(request.clientId(), request.enchereId(), request.montant());
    }

    @PostMapping("/manuelle")
    public void deposerOffreManuelle(@Valid @RequestBody OffreRequest request) {
        this.offreManuelleService.deposerOffre(request.clientId(), request.enchereId(), request.montant());
    }
}
