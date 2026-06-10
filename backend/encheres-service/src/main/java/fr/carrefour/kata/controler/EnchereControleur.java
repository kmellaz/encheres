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

@RestController
@RequestMapping("api/encheres")
@RequiredArgsConstructor
public class EnchereControleur {

    private final EnchereService enchereService;

    @GetMapping
    public ResponseEntity<List<EnchereDto>> trouverEncheresActives() {
        return ResponseEntity.status(HttpStatus.OK).body(enchereService.trouverEncheresActives());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnchereDto> trouverEnchereParId(@NotNull @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(enchereService.trouverEnchereParId(id));
    }
}
