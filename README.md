## Table of Contents
1. [Projet](#general-info)
2. [Technologies](#technologies)
3. [Installation](#installation)
4. [Points d'acces demandes](#point-acces-demandes)

 
# Projet: API RESTFul

Concevoir et développer les points d'accès pour les fonctionnalités CRUD des sondages 
(et seulement les sondages, pas le système de votes). Vous devrez créer le backend 
en intégralité avec la base de données MySQL, le modèle de données JPA et le serveur Spring Boot.

## Technologies: Les points d'accès demandés sont

La base de données MySQL, le modèle de données JPA et le serveur Spring Boot

## Installation
> Creer les packages: Controller, Dao , Entity
> Ensuite creer la classe SondagesController dans le package Controller.
> Egalement, creer la classe SondageRepository dans le package Dao.
> Et finalment creer la classe Sondages dans le Package Entity.

### Instalations dans chaque classe:
>Classe Entity:
* Constructeur de la classe Sondages.
* @param description La description du sondage.
* @param question La question posée dans le sondage.
* @param date_creation La date de création du sondage.
* @param date_cloture La date de clôture du sondage.
* @param nom Le nom de l'auteur du sondage.

>Classe SondagesController:
* Constructeur de la classe SondagesController.
* On ajoute deux sondages de test à la création de l'objet.
* Renvoie tous les sondages disponibles:
* * Récupère le sondage correspondant à l'identifiant spécifié.
* * Ajoute un nouveau sondage à la base de données.
* * Met à jour le sondage correspondant à l'identifiant spécifié.
* * Supprime le sondage correspondant à l'identifiant spécifié.


## Les points d'accès demandés sont :

* GET/sondages : Liste de tous les sondages dont la date de cloture est dans le futur.
* GET/sondages/{id} : Lecture du sondage {id} et retour au format JSON.
* POST/sondages : Création d'un nouveau sondage puis réponse HTTP 200.
* PUT/sondages/{id} : Modification du sondage {id} puis réponse HTTP 200.
* DELETE/sondages/{id} : Suppression du sondage {id} puis réponse HTTP 200.
