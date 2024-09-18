Gestion de tâches - Spring Boot
Description

Ce projet est une application de gestion de tâches permettant aux utilisateurs d'assigner des tâches à des équipes pour travailler sur un projet. L'application expose également des API REST pour la gestion des données.
Fonctionnalités

    Gestion des projets : Créer, modifier, obtenir un/tous les élément(s), supprimer un projet.
    Gestion des membres : Chaque membre travaille sur un projet .
    Gestion des équipes :Créer, modifier, obtenir un/tous les élément(s), supprimer une équipe .
    Gestion des notifications :Créer, obtenir un/tous les élément(s), supprimer une notification .
    Gestion des tâches :Créer, modifier, obtenir un/tous les élément(s) , supprimer une tâche.
    API REST : Toutes les entités (Forum, Sujet, Message) sont accessibles via des endpoints RESTful.

Structure du projet

Les principales entités du projet sont :

    Basée sur l'achitecture en couche

    Project : Représente le projet sur lequel les membres d'une équipe travaillent.
    Member : Un appartient à une équipe .
    Task : une tâche est assignée à une équipe.
    Notification : une notification est envoyée pour chaque action sur une entité.
    Team : une équipe travaille sur un projet.

Technologies utilisées

    Java 17
    Spring Boot 3.x
    Spring Data JPA pour l'accès aux données.
    Hibernate pour la gestion ORM.
    PostgreSQL pour la base de données.
    Maven pour la gestion des dépendances.
    Lombok pour simplifier le code (réduire les getters/setters).
    PostMan documentation de l'API.

Prérequis

    Java 17+
    Maven
    PostgreSQL

Installation

Clone project on your local machine

git clone https://github.com/angeBerthe/apiGestionTasks.git

Got to project directory

cd apiGestionTasks

Setting up your application.proprieties file

spring.datasource.url=jdbc:postgresql://localhost:5432/(gestTask_bd)
  spring.datasource.username=(postgres)
spring.datasource.password=(ange)
spring.jpa.hibernate.ddl-auto=create

Command for run project

mvn clean install
mvn spring-boot:run

    L'api sera accessible à l'adresse [http://localhost:8081/api](http://localhost:8081 /api).

Documentation de l'api disponible via ce lien:
(https://documenter.getpostman.com/view/33535322/2sAXqqdiSe)
