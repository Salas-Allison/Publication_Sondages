package com.example.publication_sondages;

import com.example.publication_sondages.controller.SondagesController;
import com.example.publication_sondages.dao.SondagesRepository;
import com.example.publication_sondages.entity.Sondages;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.List;

/**

 Cette classe est utilisée pour tester le fonctionnement des endpoints du contrôleur {@link SondagesController}
 à travers des requêtes HTTP simulées. Les tests sont ordonnés en fonction de l'annotation @Order.

 L'annotation @SpringBootTest est utilisée pour charger le contexte Spring pour les tests.
 Le port aléatoire est utilisé pour éviter les conflits de port dans les tests.
 La classe utilise également la bibliothèque TestRestTemplate pour effectuer les requêtes HTTP simulées.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SondagesTest {

    @Autowired
    private TestRestTemplate restTemplate;

    public static final String URL = "http://localhost:8080/rest/sondages";


    private static Sondages testSondages;

    @Test
    @Order(1)
    public void testAddSondages() {
        Sondages sondages = new Sondages("Description du sondage", "Question du sondage", LocalDate.now(), LocalDate.now().plusDays(5), "Allison");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Sondages> request = new HttpEntity<>(sondages, headers);
        ResponseEntity<Sondages> response = restTemplate.postForEntity("/rest/sondages/", request, Sondages.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(sondages.getDescription(), response.getBody().getDescription());
        assertEquals(sondages.getQuestion(), response.getBody().getQuestion());
        assertEquals(sondages.getNom(), response.getBody().getNom());
        testSondages = response.getBody();
    }
    @Test
    @Order(2)
    public void testGetSondades() {
            ResponseEntity<List<Sondages>> response = restTemplate.exchange(
                    "/rest/sondages/",
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<Sondages>>() {});

            assertEquals(HttpStatus.OK, response.getStatusCode());

            List<Sondages> sondages = response.getBody();
            assertNotNull(sondages);
            assertFalse(sondages.isEmpty());
    }
    @Test
    @Order(3)
    public void testGetId(){
        testSondages.getId();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Sondages> entity = new HttpEntity<>(testSondages, headers);

        ResponseEntity<Sondages> response = restTemplate.exchange("/rest/sondages/"+ testSondages.getId() , HttpMethod.GET, entity, Sondages.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Sondages newSondages = response.getBody() ;
        assertEquals(testSondages.getId(), newSondages.getId());
    }
    @Test
    @Order(4)
    public void testPutSondades() {
        testSondages.setQuestion("Modifier la question");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Sondages> entity = new HttpEntity<>(testSondages, headers);

        ResponseEntity<Sondages> response = restTemplate.exchange("/rest/sondages/"+ testSondages.getId() , HttpMethod.PUT, entity, Sondages.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        Sondages newSondages = response.getBody() ;
        assertEquals(testSondages.getId(), newSondages.getId());
        assertEquals("Modifier la question", newSondages.getQuestion() );
    }
    @Test
    @Order(5)
    public void testDeleteSondades() {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Sondages> entity = new HttpEntity<>(testSondages, headers);

            restTemplate.delete("/rest/sondages/" + testSondages.getId());
            ResponseEntity<Sondages> response = restTemplate.getForEntity("/rest/sondages/" + testSondages.getId(), Sondages.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
