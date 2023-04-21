package com.example.publication_sondages.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="sondages")
public class Sondages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String question;
    private LocalDate date_creation;
    private LocalDate date_cloture;
    private String nom;

    /**
     * Constructeur de la classe Sondages.
     * @param description La description du sondage.
     * @param question La question posée dans le sondage.
     * @param date_creation La date de création du sondage.
     * @param date_cloture La date de clôture du sondage.
     * @param nom Le nom de l'auteur du sondage.
     */

    public Sondages(String description, String question, LocalDate date_creation, LocalDate date_cloture, String nom) {
        this.description = description;
        this.question = question;
        this.date_creation = date_creation;
        this.date_cloture = date_cloture;
        this.nom = nom;
    }

    public Sondages() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public LocalDate getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(LocalDate date_creation) {
        this.date_creation = date_creation;
    }

    public LocalDate getDate_cloture() {
        return date_cloture;
    }

    public void setDate_cloture(LocalDate date_cloture) {
        this.date_cloture = date_cloture;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
