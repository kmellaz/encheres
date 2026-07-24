package fr.carrefour.kata;

import fr.carrefour.kata.dto.EnchereDto;
import fr.carrefour.kata.request.OffreRequest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestControleurs {

    private static String BASE_URL = "http://localhost:%s/api/encheres";

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setUp() {
        BASE_URL = String.format(BASE_URL, port);
    }

    /**
     * Tester la requete de consultation du détail d'une enchère
     *
     */
    @Test
    @Order(1)
    public void testTrouverEnchereParId(){
        Long idEnchere = 1L;
        this.webTestClient.get().uri(BASE_URL+"/{idEnchere}", idEnchere).exchange().expectStatus().isOk();
    }

    /**
     * Tester le requete de consultation des clients injetctés dans le système
     *
     */
    @Test
    @Order(2)
    public void testTrouverClients(){
        this.webTestClient.get().uri(BASE_URL+"/clients").exchange().expectStatus().isOk();
    }

    /**
     * Tester la requete de consultation des enchères actives dans le système
     *
     */
    @Test
    @Order(3)
    public void testTrouverEncheresActives(){
        this.webTestClient.get().uri(BASE_URL).exchange().expectStatus().isOk();
    }


    /**
     * tester la création d'une offre automatique défini par un client avec un montant maximum pour une enchère donnée.
     */
    @Test
    @Order(4)
    public void testCreerOffreAuto(){
        this.webTestClient.post().uri(BASE_URL+"/offres/auto")
                .bodyValue(new OffreRequest(3L, 2L, BigDecimal.valueOf(500.0)))
                .exchange().expectStatus().isOk();
    }

    /**
     * tester la création par un client d'une offre manuelle avec un montant (> monatntCourant de l'encher)
     * pour une enchère donnée et vérifier que le montant courant de l'enchère est mis à jour avec le montant de l'offre manuelle créée
     */
    @Test
    @Order(5)
    public void testCreerOffreManuelle(){
        this.webTestClient.post().uri(BASE_URL+"/offres/manuelle")
                .bodyValue(new OffreRequest(4L, 1L, BigDecimal.valueOf(190.0)))
                .exchange().expectStatus().isOk();

        this.webTestClient.get().uri(BASE_URL+"/{idEnchere}", 1L).exchange()
                .expectStatus().isOk()
                .expectBody(EnchereDto.class)
                .value(enchere -> {
                    assertEquals(1L, enchere.getId());
                    assertEquals("Vélo électrique", enchere.getDescription());
                    assertEquals(new BigDecimal("190.00"), enchere.getMontantCourant());
                });

    }

    /**
     * Déclencher le cas métier qui doit provoquer l'exception MontantEnchereInsuffisant
     * lorsque le montant misé est inférieur au montant courant de l'enchère
     */
    @Test
    @Order(6)
    public void testCreerOffreManuelleException(){
        this.webTestClient.post().uri(BASE_URL+"/offres/manuelle")
                .bodyValue(new OffreRequest(4L, 1L, BigDecimal.valueOf(140.0)))
                .exchange().expectStatus().isEqualTo(HttpStatus.CONFLICT)
                .expectBody()
                .jsonPath("$.message").toString().contains("Le montant doit être supérieur au montant courant de l'enchère");
    }

    /**
     * Tester le cas ou le sytème surencherit automatiquement pour le client qui a configuré une enchère automatique
     * en augmentant le montant d'un pas de 1 euro.
     * Sénario :
     * Montant encours de l'enchere d'id 2 égale à 220 euro
     * le client 3 a proposé une offre max à 500 euro pour cette enchere (voir testCreerOffreAuto())
     * le client 4 surencherit avec un montant de 230 euro
     * le système surencherit automatiquement pour le client 3 avec le montant 231 euro
     */
    @Test
    @Order(7)
    public void testSurencherirAutomatique(){

        this.webTestClient.post().uri(BASE_URL+"/offres/manuelle")
                .bodyValue(new OffreRequest(4L, 2L, BigDecimal.valueOf(230.0)))
                .exchange().expectStatus().isOk();

        this.webTestClient.get().uri(BASE_URL+"/{idEnchere}", 2L).exchange()
                .expectStatus().isOk()
                .expectBody(EnchereDto.class)
                .value(enchere -> {
                    assertEquals(2L, enchere.getId());
                    assertEquals("Téléviseur 4K", enchere.getDescription());
                    assertEquals(new BigDecimal("231.00"), enchere.getMontantCourant());
                });

    }

    /**
     * Tester la requete de consultation d'un client par son id
     *
     */
    @Test
    @Order(8)
    public void testTrouverClientParId(){
        this.webTestClient.get().uri(BASE_URL+"/clients/{id}", 1L).exchange().expectStatus().isOk();
    }



}
