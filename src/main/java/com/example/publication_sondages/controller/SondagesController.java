package com.example.publication_sondages.controller;

import com.example.publication_sondages.dao.SondagesRepository;
import com.example.publication_sondages.entity.Sondages;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
/**
 * Constructeur de la classe SondagesController.
 * @param "fr" le repository des sondages.
 */
@RestController
@RequestMapping("/rest/sondages")
public class SondagesController {

    // On ajoute deux sondages de test à la création de l'objet.
   public  SondagesController(SondagesRepository fr){
        this.repo = fr;

        //this.repo.save(new Sondages("Travailler sur Git", "Comment creer des branches", LocalDate.now(), LocalDate.of(2023, 9,23),"Dupont Jhan"));
        //this.repo.save(new Sondages("Spring Boot", "Documentation sur Spring Boot", LocalDate.now(), LocalDate.of(2023,6,23),"Jauoen Gaston"));
    }
    private SondagesRepository repo;
    /**
     * Renvoie tous les sondages disponibles.
     * @return une liste de tous les sondages disponibles.
     */
    @GetMapping("/")
    public List<Sondages> getSondages() {
        return repo.findAll();
    }
    /**
     * Récupère le sondage correspondant à l'identifiant spécifié.
     * @param id l'identifiant du sondage à récupérer.
     * @return le sondage correspondant à l'identifiant spécifié, ou un nouveau sondage si aucun sondage n'est trouvé.
     */
    @GetMapping("/{id}")
    public Sondages getSondages(@PathVariable long id){
        return repo.findById(id).orElse(new Sondages());
    }
    /**
     * Ajoute un nouveau sondage à la base de données.
     * @param newSondages le nouveau sondage à ajouter.
     * @return le sondage ajouté avec son identifiant mis à jour.
     */
    @PostMapping("/")
    public  Sondages addSondages(@RequestBody Sondages newSondages){
        return repo.save(newSondages);
    }
    /**
     * Met à jour le sondage correspondant à l'identifiant spécifié.
     * @param newSondages le nouveau sondage avec les modifications à appliquer.
     * @param id l'identifiant du sondage à mettre à jour.
     * @return le sondage mis à jour avec ses nouvelles valeurs.
     */
    @PutMapping("/{id}")
    public Sondages updateSondages(@RequestBody Sondages newSondages, @PathVariable long id) {
        return repo.findById(id)
                .map(sondages -> {
                    sondages.setDescription(newSondages.getDescription());
                    sondages.setQuestion(newSondages.getQuestion());
                    sondages.setDate_creation(newSondages.getDate_creation());
                    sondages.setDate_cloture(newSondages.getDate_cloture());
                    sondages.setNom(newSondages.getNom());
                    return  repo.save(sondages);
                })
                .orElseGet(() -> {
                    newSondages.setId(id);
                    return repo.save(newSondages);
                });
    }
    /**
     * Supprime le sondage correspondant à l'identifiant spécifié.
     * @param id l'identifiant du sondage à supprimer.
     */
    @DeleteMapping("/{id}")
    public void delSondages(@PathVariable long id){
        repo.deleteById(id);
    }

}

