package fr.carrefour.kata.controler;

import fr.carrefour.kata.dto.EnchereDto;
import fr.carrefour.kata.service.EnchereService;
import jakarta.validation.Valid;
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
 * Contrôleur REST exposant les endpoints relatifs aux enchères.
 *
 * Endpoints fournis :
 * - GET /api/encheres : liste des enchères actives
 * - GET /api/encheres/{id} : détail d'une enchère
 *
 */
@RestController
@RequestMapping("api/encheres")
@RequiredArgsConstructor
public class EnchereControleur {

    private final EnchereService enchereService;

    /**
     * Endpoint GET pour récupérer toutes les enchères.
     *
     * @return ResponseEntity contenant la liste des {@link fr.carrefour.kata.dto.EnchereDto}
     */
    @GetMapping
    public ResponseEntity<List<EnchereDto>> trouverEncheres() {
        return ResponseEntity.status(HttpStatus.OK).body(enchereService.trouverEncheres());
    }

    /**
     * Endpoint GET pour récupérer une enchère par son identifiant.
     *
     * @param id identifiant de l'enchère (non null)
     * @return ResponseEntity contenant le {@link fr.carrefour.kata.dto.EnchereDto}
     */
    @GetMapping("/{id}")
    public ResponseEntity<EnchereDto> trouverEnchereParId(@NotNull @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(enchereService.trouverEnchereParId(id));
    }
}
